package sg.edu.iss.caps.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.model.User;
import sg.edu.iss.caps.service.UserSessionService;
import sg.edu.iss.caps.util.MenuNavBarUtil;
import sg.edu.iss.caps.util.UserSessionUtil;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
    UserSessionService userSessionService;
	
	@RequestMapping("")
	public String loadHomePage(Model model) {
		User user = userSessionService.findUserSession();
    	MenuNavBarUtil.generateNavBar(user, model);
		return "index";
	}
	
	@RequestMapping("/contact")
	public String loadContactPage(Model model) {
		User user = userSessionService.findUserSession();
    	MenuNavBarUtil.generateNavBar(user, model);
		return "contact";
	}

}
