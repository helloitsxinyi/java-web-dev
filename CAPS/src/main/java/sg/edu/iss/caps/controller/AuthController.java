package sg.edu.iss.caps.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.iss.caps.model.Account;
import sg.edu.iss.caps.model.ChangePWRequest;
import sg.edu.iss.caps.model.User;
import sg.edu.iss.caps.model.UserStatus;
import sg.edu.iss.caps.service.AccountAuthenticationService;
import sg.edu.iss.caps.service.UserSessionService;
import sg.edu.iss.caps.util.UserSessionUtil;
import sg.edu.iss.caps.validator.AccountValidator;

@Controller
public class AuthController {
	
	@Autowired
	private AccountAuthenticationService accAuthService;
	
	@Autowired
	private AccountValidator aValidator;
	
	@Autowired
    UserSessionService userSessionService;
	
	@InitBinder
	private void initAccountBinder(WebDataBinder binder) {
		binder.addValidators(aValidator);
	}
	

    @GetMapping("/login")
    public String loadLoginPage(Model model) {
    	//Display login page
    	Account account = new Account();
    	model.addAttribute("account",account);
    	model.addAttribute("repeatlogin", false);
        return "login";
    }

    @PostMapping(path = "/login/authenticate")
    public String login (@ModelAttribute("account") @Valid Account account, BindingResult result, Model model) {
    	//To authenticate the login
    	//Admin - capslbj, Student - troy, Lecturer - yuenkwan
    	if(result.hasErrors()) {
    		model.addAttribute("repeatlogin", false);
    		return "login";
    	}
    	//To check if the user exist
    	User user = accAuthService.authenticateAccount(account);
    	if(user == null || user.getUserStatus() == UserStatus.INACTIVE) {
    		//Return back to login page if fail to authenticate
    		//Return back if the account is inactive
        	model.addAttribute("account",account);
        	model.addAttribute("repeatlogin", true);
        	//Clear any old password reset request since login is successful
        	accAuthService.updatePasswordResetRequest(user,null);
    		return "login";
    	}
    	//Set the user into the session data
    	userSessionService.setUserSession(user);
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout (Model model) {
    	userSessionService.removeUserSession();
        return "redirect:/home";
    }
    
    /*
    Methods for forget password handling
    	Forget password
    		- To display the page to enter email
    	Request password reset 
    		- To receive an email from the forget password page
    		- Validate the email address
    		- Send an email containing a reset link 
    			- Reset link has a uuid that is valid for 1 hour
    		- Store the request in the model ChangePWRequest
    	Reset password by link
    		- Receive the uuid as a parameter from the path
    		- Validate that the uuid is correct against the repository
    		- Divert the user to a change password page if the link is correct
    	Reset new password
    		- To validate the uuid
    		- To replace the old password with a new one
    */
    
    @GetMapping("/login/forget-password")
    public String forgetPassword (Model model) {
        // To display the page which allows the reset password request via email
        return "loginForgetPassword";
    }
    
    @GetMapping("/login/request-reset")
    public String requestPasswordReset (@RequestParam("emailval") String emailstr,HttpServletRequest request, Model model) {
        // To handle a request for password reset

    	//Check if the email is a valid one
    	User user = accAuthService.findUserByEmail(emailstr);
    	
    	//Generate the url for the password reset (e.g. http://localhost:8080/login/passwordreset)
    	String url = request.getRequestURL().toString().replace(request.getRequestURI(),"/");
    	
    	if(user != null) {
    		// Send email which contain a link with a UUID that is valid for 1 hour
    		accAuthService.sendPasswordResetEmail(user,url);
    	}
        return "loginSentReset";
    }

    @GetMapping("/login/passwordreset")
    public String resetPasswordByLink (@RequestParam("resetId") String uuidStr, Model model) {
    	//Checks if the uuid is valid and redirect to password reset page
    	if(uuidStr == null || uuidStr.length() != 36) {
    		//No uuid found or uuid is invalid
    		return "redirect:/login";
    	}
    	//Find the request by checking the uuid
    	ChangePWRequest chPwRequest = accAuthService.findPasswordResetRequestById(uuidStr);
    	if(chPwRequest == null) {
    		//No request found (invalidate / expired uuid)
    		return "redirect:/login";
    	}
    	//Valid request exist. Display the password reset form
    	model.addAttribute("resetId",uuidStr);
    	model.addAttribute("repeatreset",false);
        return "loginPasswordResetForm";
    }
    
    @PostMapping("/login/reset-new-password")
    public String resetNewPassword (@RequestParam("resetId") String uuidStr,@RequestParam("newPW") String newPW,
    		@RequestParam("newCmfPW") String newCmfPW,Model model) {
    	// To perform the password reset
        
    	if(!newPW.equals(newCmfPW)) {
    		// To check if both password are the same
    		model.addAttribute("resetId",uuidStr);
    		model.addAttribute("repeatreset",true);
    		return "loginPasswordResetForm";
    	}
    	//Perform change of password if password are the same
    	accAuthService.changeNewPasswordUUid(uuidStr,newPW);
    	
        return "redirect:/login";
    }
    

}
