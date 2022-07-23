package sg.edu.iss.caps.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.caps.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	List<Student> findByUsername(String username); 
	
	List<Student> findStudentByFirstName(String firstName);
	
	@Query("SELECT s FROM Student s WHERE s.username = :u_name AND s.passwordHash = :pass")
	List<Student> autenticateAccount(@Param("u_name") String username,@Param("pass") byte[] passwordHash);

	Student findFirstByUsername(String username);
	Student findFirstByEmail(String email);
	
	@Query("Select s from Student s where user_status = 'ACTIVE'")
	List<Student>findAllActiveStudents();
}
