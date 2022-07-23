package sg.edu.iss.caps.service;


import java.util.UUID;

import sg.edu.iss.caps.model.Account;
import sg.edu.iss.caps.model.ChangePWRequest;
import sg.edu.iss.caps.model.User;

public interface AccountAuthenticationService {
	
	//To authenticate the login of the user
	public User authenticateAccount(Account account);
	
	//To find if an email is valid
	public User findUserByEmail(String email);
	
	//To send an email with link to reset password
	public void sendPasswordResetEmail(User user, String hostUrl);
	
	//To update the password reset request database
	public boolean updatePasswordResetRequest(User user, UUID uuid);
	
	//To find the password reset request from the database
	public ChangePWRequest findPasswordResetRequestById(String uuidStr);
	
	//To change a new password using uuid link
	public void changeNewPasswordUUid(String uuidStr, String newPW);
	
	
}
