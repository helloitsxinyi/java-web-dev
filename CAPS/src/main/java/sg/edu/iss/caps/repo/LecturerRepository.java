package sg.edu.iss.caps.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.caps.model.Lecturer;


public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {

	Lecturer findFirstByUsername(String username);
	Lecturer findFirstByEmail(String email);
	
	@Query("Select l from Lecturer l where user_status = 'ACTIVE'")
	ArrayList<Lecturer>findAllActiveLecturers();
	
	@Query("Select l from Lecturer l join l.teachCourses lc WHERE lc.courseCode = :cId")
	ArrayList<Lecturer>findLecturerByCourseId(@Param("cId") String courseCode);
	
	@Query("Select l from Lecturer l where l NOT IN (Select l2 from Lecturer l2 join l2.teachCourses lc WHERE lc.courseCode = :cId)")
	ArrayList<Lecturer>findAvailLecturerByCourseId(@Param("cId") String courseCode);
}
