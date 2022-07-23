package sg.edu.iss.caps.repotest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import sg.edu.iss.caps.CapsApplication;
import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.CourseStatus;
import sg.edu.iss.caps.model.Lecturer;
import sg.edu.iss.caps.model.Student;
import sg.edu.iss.caps.repo.CourseRepository;
import sg.edu.iss.caps.repo.StudentRepository;
import sg.edu.iss.caps.service.LecturerCourseService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CapsApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class CourseRepositoryTest {

	@Autowired
	CourseRepository courseRepo;
	@Autowired
	StudentRepository srepo;

	@Test
	@Order(1)
	public void testCreateCouse() {
		Course c = new Course("UT9999", "Creating Fun Unit Tests" ,"Learn to make fun and innovative unit tests!", "10", 10, CourseStatus.OPEN);
		courseRepo.saveAndFlush(c);
		System.out.println("New course created: " + c.toString());	
		Assertions.assertNotNull(c);
	}

	@Test
	@Order(2)
	public void testUpdateCourse() {
		Course result = courseRepo.findCourseCode("UT9999");
		System.out.println("Before update: Title=" + result.getCourseTitle() + " Description=" + result.getCourseDescription());
		assertEquals(result.getCourseTitle(), "Creating Fun Unit Tests", "Result title does not match");
		assertEquals(result.getCourseDescription(), "Learn to make fun and innovative unit tests!", "Result description does not match");
		result.setCourseTitle("updateCourseTitle");
		result.setCourseDescription("updateCourseDescription");
		courseRepo.saveAndFlush(result);
		System.out.println("After update: Title=" + result.getCourseTitle() + " Description= " + result.getCourseDescription());
		assertNotEquals(result.getCourseTitle(), "Creating Fun Unit Tests", "Result title does not match");
		assertNotEquals(result.getCourseDescription(), "Learn to make fun and innovative unit tests!", "Result description does not match");
	}
	
	@Test
	@Order(3)
	public void testDeleteCourse() {
		Course result = courseRepo.findCourseCode("UT9999");
		Assertions.assertNotNull(result);
		System.out.println("Existing course found: Title=" + result.getCourseTitle() + " Description= " + result.getCourseDescription());
		courseRepo.delete(result);
		courseRepo.flush();
		result = courseRepo.findCourseCode("UT9999");
		Assertions.assertNull(result);
	}
	
	@Test
	@Order(4)
	public void testFindLecturer() {
		Course result = courseRepo.findCourseCode("SA4005");
		assertEquals(result.getCourseTitle(), "The Complete Web Developer Masterclass: Beginner To Advanced", "Result title does not match");
		assertEquals(result.getCourseCapacity(), 15, "Result capacity does not match");
		assertEquals(result.getCourseCredits(), "6", "Result credits does not match");
	}

	@Test
	@Order(5)
	public void findCourseNotTakenByStudent() {
		Student s = srepo.findFirstByUsername("pratap");
		List<Course> rlist = courseRepo.findCourseNotTaken(s);
		Assertions.assertEquals(rlist.size(),11);
	}
	
	@Test
	@Order(6)
	public void findCourseNotTakenByStudentSearch() {
		Student s = srepo.findFirstByUsername("pratap");
		List<Course> rlist = courseRepo.findCourseNotTakenSearch(s, "007");
		Assertions.assertEquals(rlist.size(),1);
	}
	
	@Test
	@Order(7)
	public void findCourseTaken() {
		Student s = srepo.findFirstByUsername("pratap");
		List<Course> rlist = courseRepo.findCourseTaken(s);
		Assertions.assertEquals(rlist.size(),2);
	}
	
	@Test
	@Order(8)
	public void findCourseCode() {
		Course result = courseRepo.findCourseCode("SA4003");
		Assertions.assertEquals(result.getCourseTitle(), "Become a Professional Web Developer - Version 3.0", "Result title does not match");
	}

	@Test
	@Order(9)
	public void findCourseLecturer() {
		List<Lecturer> result = courseRepo.findCourseLecturers("SA4002");
		Assertions.assertEquals(result.size(),3);
	}

}
