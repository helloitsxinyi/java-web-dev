package sg.edu.iss.caps.service;

import java.util.List;

import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.CourseStudent;
import sg.edu.iss.caps.model.Student;

public interface StudentCourseService {
	
	public CourseStudent CreateNewCourseStudent(Student student, String courseCode);
	public List<Course> findEnrolledCourse(Student s);
	public List<CourseStudent> findCourseStudent(Student s);
	public List<CourseStudent> findStudentGrades(Student s);
	public void unenrollStudentFromCourse(Integer studentId, String courseCode);
	public double getGPA(List<CourseStudent> courseGrades, Student s);
	
}
