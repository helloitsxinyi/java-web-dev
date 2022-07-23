package sg.edu.iss.caps.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.CourseStudent;
import sg.edu.iss.caps.model.Student;

public interface CourseStudentRepository extends JpaRepository<CourseStudent, Integer> {
	
	@Query("SELECT cs FROM CourseStudent cs WHERE cs.student = :s AND cs.course = :c")
	public List<CourseStudent> findCSByStudentAndCourse(Student s, Course c);
	
	@Query("SELECT cs FROM CourseStudent cs WHERE course_course_code = :courseCode")
	ArrayList<CourseStudent>findCSByCourse(@Param("courseCode") String courseCode);
	
	@Query("SELECT cs FROM CourseStudent cs WHERE cs.student = :s AND cs.courseStudentStatus IN ('COMPLETED','FAILED')")
	public List<CourseStudent> findGrades(Student s);
	
	@Query("SELECT cs FROM CourseStudent cs WHERE cs.student = :s AND cs.course = :c")
	public CourseStudent findCourseStudent(Student s, Course c);
	
	@Query("SELECT cs FROM CourseStudent cs WHERE cs.student = :s")
	public List<CourseStudent> findCourseStudentByStudent(@Param("s") Student s);
}
