package sg.edu.iss.caps.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.caps.model.ChangePWRequest;

public interface ChangePWRequestRepository extends JpaRepository<ChangePWRequest, UUID> {
	
	@Query("SELECT cr FROM ChangePWRequest cr WHERE cr.lecturer.lecturerId = :lecId")
	List<ChangePWRequest> findByLecturerId(@Param("lecId") Integer id);
	
	@Query("SELECT cr FROM ChangePWRequest cr WHERE cr.student.studentId = :stuId")
	List<ChangePWRequest> findByStudentId(@Param("stuId") Integer id);
}
