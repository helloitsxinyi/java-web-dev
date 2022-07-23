package sg.edu.iss.caps.service;

import sg.edu.iss.caps.model.Administrator;
import sg.edu.iss.caps.model.Lecturer;
import sg.edu.iss.caps.model.Student;
import sg.edu.iss.caps.model.User;

public interface UserSessionService {
	
	public User findUserSession();
	
	public Lecturer findLecturerSession();
	
	public Student findStudentSession();
	
	public Administrator findAdminSession();
	
	public void setUserSession(User user);
	
	public void removeUserSession();
	
	

}
