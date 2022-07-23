package sg.edu.iss.caps.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.caps.model.Administrator;
import sg.edu.iss.caps.model.Lecturer;
import sg.edu.iss.caps.model.Student;
import sg.edu.iss.caps.model.User;

@Service
public class UserSessionServiceImpl implements UserSessionService {

	@Autowired
	HttpSession session;

	private final String sessionIdentity = "loginUser";

	@Override
	public User findUserSession() {
		User user = null;
		try {
			// Try to find and cast the into user
			user = (User) session.getAttribute(sessionIdentity);
		} catch (ClassCastException e) {
			// Fail to cast. Object is not a user.
			return null;
		}
		return user;
	}

	@Override
	public Lecturer findLecturerSession() {
		// Find the User
		// Verify that user is a student
		User user = findUserSession();
		if (user == null || !(user instanceof Lecturer)) {
			// If no user or user not instanceof lecturer
			return null;
		}
		return (Lecturer) user;
	}

	@Override
	public Student findStudentSession() {
		// Find the User
		// Verify that user is a student
		User user = findUserSession();
		if (user == null || !(user instanceof Student)) {
			// If no user or user not instanceof student
			return null;
		}
		return (Student) user;
	}

	@Override
	public Administrator findAdminSession() {
		// Find the User
		// Verify that user is a student
		User user = findUserSession();
		if (user == null || !(user instanceof Administrator)) {
			// If no user or user not instanceof lecturer
			return null;
		}
		return (Administrator) user;
	}

	@Override
	public void setUserSession(User user) {
		session.setAttribute(sessionIdentity,user);
	}

	@Override
	public void removeUserSession() {
		session.removeAttribute(sessionIdentity);
	}

}
