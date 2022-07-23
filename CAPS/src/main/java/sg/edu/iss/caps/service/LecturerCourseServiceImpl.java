package sg.edu.iss.caps.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.CourseStudent;
import sg.edu.iss.caps.model.CourseStudentStatus;
import sg.edu.iss.caps.model.Grade;
import sg.edu.iss.caps.model.Lecturer;
import sg.edu.iss.caps.repo.CourseRepository;
import sg.edu.iss.caps.repo.CourseStudentRepository;
import sg.edu.iss.caps.repo.LecturerRepository;
import sg.edu.iss.caps.util.GradeUtil;

@Service
public class LecturerCourseServiceImpl implements LecturerCourseService{

    @Autowired
    CourseStudentRepository courseStudentRepo;
    @Autowired
    CourseRepository courseRepo;
    @Autowired
    LecturerRepository lecturerRepo;

    @Transactional
	@Override
	public List<Course> findLecturerAssignedCourses(Lecturer l) {
    	List<Course>teachCoursesList = l.getTeachCourses();
    	return teachCoursesList;
    }
    
    @Transactional
	@Override
	public List<CourseStudent> findStudentsInCourse(String courseCode){
    	List<CourseStudent> courseStudentList = courseStudentRepo.findCSByCourse(courseCode);
    	return courseStudentList;
    }

    @Transactional
	@Override
	public CourseStudent findStudentGrade(Integer courseStudentId){
    	CourseStudent s = courseStudentRepo.findById(courseStudentId).get();
    	return s;
	}
    
    @Transactional
	@Override
	public void saveStudentGrade(CourseStudent cs, int score) {
		cs.setScore(score);
		try {
			cs.setGrade(GradeUtil.calculateGrade(score));
			if (cs.getGrade() == Grade.F) {
				cs.setCourseStudentStatus(CourseStudentStatus.FAILED);
			} else{
				cs.setCourseStudentStatus(CourseStudentStatus.COMPLETED);
			};
		} catch (Exception e) {
			e.printStackTrace();
		}
		courseStudentRepo.save(cs);
    }
}
