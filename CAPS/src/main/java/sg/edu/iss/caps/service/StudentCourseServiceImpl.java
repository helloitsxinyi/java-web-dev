package sg.edu.iss.caps.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.CourseStudent;
import sg.edu.iss.caps.model.CourseStudentStatus;
import sg.edu.iss.caps.model.Grade;
import sg.edu.iss.caps.model.Student;
import sg.edu.iss.caps.repo.CourseRepository;
import sg.edu.iss.caps.repo.CourseStudentRepository;
import sg.edu.iss.caps.repo.StudentRepository;
import sg.edu.iss.caps.util.GradeUtil;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {
	
	@Autowired
	CourseStudentRepository courseStudentRepo;
	
	@Autowired
	CourseRepository courseRepo;
	
	@Autowired
	StudentRepository stuRepo;
	
	@Override
	public CourseStudent CreateNewCourseStudent(Student student, String courseCode) {
		//To validate the course code
		//To check that the student has not registered for the course
		Course course = courseRepo.findById(courseCode).get();
		if(course == null) {
			return null;
		}
    	List<CourseStudent> courseStudentlist = courseStudentRepo.findCSByStudentAndCourse(student, course);
		
    	if(courseStudentlist.size() > 0) {
    		//Cannot register if course already present
			return null;
    	}
    	
    	CourseStudent cs = new CourseStudent(student,course);
    	try {
    		courseStudentRepo.saveAndFlush(cs);
    	}
    	catch(Exception e) {
    		//Failed to save repo
    		
    		return null;
    	}
    	
		return cs;
	}
	
	@Override
	public List<Course> findEnrolledCourse(Student s) {
		// Find the courses that the student have taken
		List<Course> courseTaken = courseRepo.findCourseTaken(s);
		return courseTaken;
	}
	
	@Override
	public List<CourseStudent> findCourseStudent(Student s){
		//Find List of CourseStudent
		List<CourseStudent> courseStudentList = courseStudentRepo.findCourseStudentByStudent(s);
		return courseStudentList;
	}
	
	
	@Override
	public List<CourseStudent> findStudentGrades(Student s){
//		List<CourseStudent> courses = courseStudentRepo.findAll();
		List<CourseStudent> courseGrades = courseStudentRepo.findGrades(s);
		return courseGrades;
	}
	
	@Override
	public void unenrollStudentFromCourse(Integer studentId, String courseCode) {
		// TODO Auto-generated method stub
		Student student = stuRepo.findById(studentId).get();
		Course course = courseRepo.findCourseCode(courseCode);
		CourseStudent courseStudent =  courseStudentRepo.findCourseStudent(student, course);
		if(courseStudent.getCourseStudentStatus() == CourseStudentStatus.ENROLLED) {
			//Only delete if status is enrolled
			courseStudentRepo.delete(courseStudent);
		}
		
	}
	
	@Override
	public double getGPA(List<CourseStudent> courseGrades, Student s) {
		double gradePoint = 0.0;
		double gpa = 0.0;
		double numerator = 0.0;
		double denominator = 0.0;
		if(courseGrades.isEmpty()) {
			//No courses taken, no GPA
			return 0;
		}
		for(CourseStudent courseGrade:courseGrades) {
			Course course = courseGrade.getCourse();
			gradePoint = GradeUtil.getGPAValue(courseGrade.getGrade());
			
			numerator += gradePoint * Integer.parseInt(course.getCourseCredits());
			denominator += Integer.parseInt(course.getCourseCredits());			
		}
		
		gpa = numerator / denominator;
		
		return gpa;
	}
}
