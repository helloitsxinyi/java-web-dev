package sg.edu.iss.caps.repotest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import sg.edu.iss.caps.CapsApplication;
import sg.edu.iss.caps.model.Role;
import sg.edu.iss.caps.model.Student;
import sg.edu.iss.caps.repo.StudentRepository;
import sg.edu.iss.caps.util.DateUtil;
import sg.edu.iss.caps.util.HashUtil;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CapsApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTest {
	
	private static final Logger LOGGER = Logger.getLogger(CapsApplication.class.getName());
	@Autowired
	StudentRepository srepo;
	
	
	@Test
	@Order(1)
	public void testCreateStudent() {
		Calendar c = Calendar.getInstance();
		byte[] hashedpw = HashUtil.getHash("johnteo","password");
		Student s = new Student("johnteo", hashedpw, "John","Teo","johnteo@issnusteamsa54.com",Role.STUDENT,c.getTime());
		
		LOGGER.info("\u001B[34m" + "Hashed : " + "\u001B[0m" + HashUtil.convertByteToHex(hashedpw));
		
		srepo.saveAndFlush(s);
		
		//Test that the student is input into Database
		Assertions.assertNotNull(s.getStudentId());
	}
	
	@Test
	@Order(2)
	public void testFindStudent() {
		Student s = srepo.findByUsername("johnteo").get(0);
		Date entrollmentDate = s.getEnrolledDate();
		//LocalDateTime entrollmentDate = s.getEnrolledDate();
		//Print out date
		LOGGER.info("\u001B[34m" + "Date Enrolled : " + "\u001B[0m" + DateUtil.ConvertFromDate(entrollmentDate));
		
		//Test that the correct person is found
		Assertions.assertEquals(s.getFirstName(),"John");
		List<Student> slist = srepo.findByUsername("johnteo");
		//Test that only 1 entry is found
		Assertions.assertEquals(slist.size(),1);
		//Test hashing of password is correct
		Assertions.assertTrue(Arrays.equals(HashUtil.getHash("johnteo","password"), slist.get(0).getPasswordHash()));
		
	}
	
	@Test
	@Order(3)
	public void testUpdateStudent() {
		Student result = srepo.findFirstByUsername("johnteo");
		System.out.println("Before update: " + result.getFirstName() + " " + result.getLastName() + " " + result.getEmail());
		assertEquals(result.getFirstName(), "John", "Result first name does not match");
		assertEquals(result.getLastName(), "Teo", "Result last name does not match");
		assertEquals(result.getEmail(), "johnteo@issnusteamsa54.com", "Result email does not match");
		result.setFirstName("updateFirstName");
		result.setLastName("updateLastName");
		result.setEmail("updateemail@issnusteamsa54.com");
		srepo.saveAndFlush(result);
		System.out.println("After update: " + result.getFirstName() + " " + result.getLastName() + " " + result.getEmail());
		assertNotEquals(result.getFirstName(), "John", "Result first name does not match");
		assertNotEquals(result.getLastName(), "Teo", "Result last name does not match");
		assertNotEquals(result.getEmail(), "johnteo@issnusteamsa54.com", "Result email does not match");	
	}
	
	@Test
	@Order(4)
	public void testDeleteStudent() {
		long beforeCount = srepo.count();
		Student s = srepo.findByUsername("johnteo").get(0);
		srepo.delete(s);
		long afterCount = srepo.count();
		//Test that student is deleted
		Assertions.assertEquals(afterCount, beforeCount - 1);
		List<Student> slist = srepo.findByUsername("johnteo");
		//Test that account is deleted (no account found)
		Assertions.assertEquals(slist.size(),0);
	}
	
	@Test
	@Order(5)
	public void testFindStudentByUsername() {
		List<Student> rlist = srepo.findByUsername("jasvinder");
		Assertions.assertEquals(rlist.size(),1);
	}
	
	@Test
	@Order(6)
	public void testFindStudentByFirstName() {
		List<Student> rlist = srepo.findStudentByFirstName("Ling");
		Assertions.assertEquals(rlist.size(),1);
	}
	
	@Test
	@Order(7)
	public void testFindStudentFirstByUsername() {
		Student result = srepo.findFirstByUsername("ling");
		assertEquals(result.getFirstName(), "Ling", "Result first name does not match");
		assertEquals(result.getLastName(), "Lu", "Result last name does not match");
		System.out.println("Existing student found: " + result.getFirstName() + " " + result.getLastName());
	}
	
	@Test
	@Order(8)
	public void testFindStudentByEmail() {
		Student result = srepo.findFirstByEmail("troybarn4@gmail.com");
		assertEquals(result.getFirstName(), "Troy", "Result first name does not match");
		assertEquals(result.getLastName(), "Barnes", "Result last name does not match");
		System.out.println("Existing student found: " + result.getFirstName() + " " + result.getLastName());
	}
	
	@Test
	@Order(9)
	public void testFindAllActiveStudent() {
		List<Student> rlist = srepo.findAllActiveStudents();
		Assertions.assertEquals(rlist.size(),13);
	}
}
