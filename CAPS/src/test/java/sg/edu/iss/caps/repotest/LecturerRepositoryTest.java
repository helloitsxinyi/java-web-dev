package sg.edu.iss.caps.repotest;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import sg.edu.iss.caps.CapsApplication;
import sg.edu.iss.caps.model.Lecturer;
import sg.edu.iss.caps.model.Role;
import sg.edu.iss.caps.repo.LecturerRepository;
import sg.edu.iss.caps.util.HashUtil;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CapsApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)

public class LecturerRepositoryTest {

	@Autowired
	LecturerRepository lecturerRepo;
	
	@Test
	@Order(1)
	public void testCreateLecturer() {
		byte[] hashedpw = HashUtil.getHash("gohmm","password");
		Lecturer l = new Lecturer("gohmm", hashedpw, "Mei Mei", "Goh", "gohmm@sa54team4.com", Role.LECTURER);
		lecturerRepo.saveAndFlush(l);
		Assertions.assertNotNull(l.getLecturerId());
		System.out.println("New lecturer created:  LecturerId= " + l.getLecturerId());
	}

	@Test
	@Order(2)
	public void testUpdateLecturer() {
		Lecturer result = lecturerRepo.findFirstByUsername("gohmm");
		System.out.println("Before update: " + result.getFirstName() + " " + result.getLastName() + " " + result.getEmail());
		assertEquals(result.getFirstName(), "Mei Mei", "Result first name does not match");
		assertEquals(result.getLastName(), "Goh", "Result last name does not match");
		assertEquals(result.getEmail(), "gohmm@sa54team4.com", "Result email does not match");
		result.setFirstName("updateFirstName");
		result.setLastName("updateLastName");
		result.setEmail("updateemail@sa54team4.com");
		lecturerRepo.saveAndFlush(result);
		System.out.println("After update: " + result.getFirstName() + " " + result.getLastName() + " " + result.getEmail());
		assertNotEquals(result.getFirstName(), "Mei Mei", "Result first name does not match");
		assertNotEquals(result.getLastName(), "Goh", "Result last name does not match");
		assertNotEquals(result.getEmail(), "gohmm@sa54team4.com", "Result email does not match");	
	}
	
	@Test
	@Order(3)
	public void testDeleteLecturer() {
		Lecturer result = lecturerRepo.findFirstByUsername("gohmm");
		Assertions.assertNotNull(result);
		System.out.println("Existing lecturer found:  LecturerId=" + result.getLecturerId()  + " " + result.getFirstName() + " " + result.getLastName());
		lecturerRepo.delete(result);
		lecturerRepo.flush();
		result = lecturerRepo.findFirstByUsername("gohmm");
		Assertions.assertNull(result);
	}
	
	@Test
	@Order(4)
	public void testFindLecturer() {
		Lecturer result = lecturerRepo.findById(10).get();
		assertEquals(result.getFirstName(), "Tri Tin", "Result first name does not match");
		assertEquals(result.getLastName(), "Nguyen", "Result last name does not match");
		assertEquals(result.getUsername(), "tritin", "Result username does not match");
	}
	
	@Test
	@Order(5)
	public void testFindLecturerFirstByUsername() {
		Lecturer result = lecturerRepo.findFirstByUsername("esthertan");
		assertEquals(result.getFirstName(), "Esther", "Result first name does not match");
		assertEquals(result.getLastName(), "Tan", "Result last name does not match");
	}
	
	@Test
	@Order(6)
	public void testFindLecturerFirstByEmail() {
		Lecturer result = lecturerRepo.findFirstByEmail("capset@sa54team4.com");
		assertEquals(result.getFirstName(), "Esther", "Result first name does not match");
		assertEquals(result.getLastName(), "Tan", "Result last name does not match");
	}
	
	@Test
	@Order(7)
	public void testFindAllActiveLecturers() {
		ArrayList<Lecturer> rlist = lecturerRepo.findAllActiveLecturers();
		Assertions.assertEquals(rlist.size(),13);
	}
}
