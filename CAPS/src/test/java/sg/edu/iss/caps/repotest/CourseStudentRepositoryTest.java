package sg.edu.iss.caps.repotest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import sg.edu.iss.caps.CapsApplication;
import sg.edu.iss.caps.repo.CourseRepository;
import sg.edu.iss.caps.repo.CourseStudentRepository;
import sg.edu.iss.caps.repo.StudentRepository;
import sg.edu.iss.caps.model.Student;
import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.CourseStudent;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CapsApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class CourseStudentRepositoryTest {

	@Autowired
	CourseStudentRepository csrepo;
	@Autowired
	StudentRepository srepo;
	@Autowired
	CourseRepository crepo;
	
	@Test
	@Order(1)
	public void findListofStudentsInCourse() {
		Student s = srepo.findFirstByUsername("abed");
		Course c = crepo.findCourseCode("SA4001");
		List<CourseStudent> rlist = csrepo.findCSByStudentAndCourse(s, c);
		Assertions.assertEquals(rlist.size(),1);
	}

	@Test
	@Order(2)
	public void findCourseWithStudents() {
		ArrayList<CourseStudent> rlist = csrepo.findCSByCourse("SA4004");
		Assertions.assertEquals(rlist.size(),5);
		ArrayList<CourseStudent> rlist2 = csrepo.findCSByCourse("SA4009");
		Assertions.assertEquals(rlist2.size(),0);
	}
	
	@Test
	@Order(3)
	public void findStudentGrades() {
		Student s = srepo.findFirstByUsername("fang");
		List<CourseStudent> rlist = csrepo.findGrades(s);
		Assertions.assertEquals(rlist.size(),2);
		Student s2 = srepo.findFirstByUsername("padma");
		List<CourseStudent> rlist2 = csrepo.findGrades(s2);
		Assertions.assertEquals(rlist2.size(),0);
	}
}
