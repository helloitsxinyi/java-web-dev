package sg.edu.iss.caps.util;

import javax.servlet.http.HttpSession;

import sg.edu.iss.caps.model.Administrator;
import sg.edu.iss.caps.model.Lecturer;
import sg.edu.iss.caps.model.Student;
import sg.edu.iss.caps.model.User;

public class UserSessionUtil {
	
	private static final String sessionIdentity = "loginUser";
	
	public static User findUser(HttpSession session) {
		User user = null;
		try{
			//Try to find and cast the into user
			user = (User) session.getAttribute(sessionIdentity);
		}
		catch(ClassCastException e){
			//Fail to cast. Object is not a user.
			return null;
		}
		return user;
	}
	
	public static Student findStudent(HttpSession session) {
		//Find the User
		//Verify that user is a student
		User user = findUser(session);
		if(user == null || !(user instanceof Student)) {
			//If no user or user not instanceof student
			return null;
		}
		return (Student) user;
	}
	
	public static Lecturer findLecturer(HttpSession session) {
		//Find the User
		//Verify that user is a student
		User user = findUser(session);
		if(user == null || !(user instanceof Lecturer)) {
			//If no user or user not instanceof lecturer
			return null;
		}
		return (Lecturer) user;
	}
	
	public static Administrator findAdmin (HttpSession session) {
		//Find the User
		//Verify that user is a student
		User user = findUser(session);
		if(user == null || !(user instanceof Administrator)) {
			//If no user or user not instanceof lecturer
			return null;
		}
		return (Administrator) user;
	}
	
	public static void setUser(HttpSession session, User user) {
		session.setAttribute(sessionIdentity,user);
	}
	
	public static void removeSession(HttpSession session) {
		session.removeAttribute(sessionIdentity);
	}
	
}
