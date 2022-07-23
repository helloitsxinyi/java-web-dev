package sg.edu.iss.caps.service;

public interface EmailService {
	public void sendResetPWMessage(String sendTo, String name, String address);
	
	public void SendCourseRegisteredEmail(String sendTo, String name, String courseCode);
}
