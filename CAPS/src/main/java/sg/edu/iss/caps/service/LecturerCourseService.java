package sg.edu.iss.caps.service;

import java.util.List;

import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.CourseStudent;
import sg.edu.iss.caps.model.Lecturer;

public interface LecturerCourseService {

	public List<Course> findLecturerAssignedCourses(Lecturer l);
	public List<CourseStudent> findStudentsInCourse(String courseCode);
	public CourseStudent findStudentGrade(Integer courseStudentId);
	public void saveStudentGrade(CourseStudent cs, int score);
}
