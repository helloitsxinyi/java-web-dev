package sg.edu.iss.caps.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.iss.caps.model.Administrator;
import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.Lecturer;
import sg.edu.iss.caps.model.User;
import sg.edu.iss.caps.repo.CourseRepository;
import sg.edu.iss.caps.repo.LecturerRepository;
import sg.edu.iss.caps.service.UserSessionService;
import sg.edu.iss.caps.util.UserSessionUtil;

@CrossOrigin(origins= "http://localhost:3000/", allowCredentials = "true")
@RestController
@RequestMapping("/api/manage/courselecturer")
public class AdminAssignCourseLecturerController {
	
	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	private LecturerRepository lecturerRepo;
	
	@Autowired
	private UserSessionService userSessionService;
	
	@GetMapping("/listCourse")
	public List<Course> getCourses(){
		//To obtain full list of courses and send back
		Administrator admin = userSessionService.findAdminSession();
		if(admin== null) {
			return null;
		}
		List<Course> courselist = courseRepo.findAll();
		return courselist;
	}
	
	@GetMapping("/listLecturer")
	public List<Lecturer> getLecturers(){
		//To obtain a list of lecturers that are not assigned to course
		Administrator admin = userSessionService.findAdminSession();
		if(admin== null) {
			return null;
		}
		List<Lecturer> lecturerlist = lecturerRepo.findAll();
		return lecturerlist;
	}
	
	@GetMapping("/getUser")
	public ResponseEntity<String> getUser(){
		User user = userSessionService.findAdminSession();
		if(user == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		String name = user.getFirstName() + " " + user.getLastName();
		return new ResponseEntity<>(name, HttpStatus.OK);
	}
	
	@GetMapping("/getNewCourse")
	public ResponseEntity<Course> getNewCourse(){
		Course course = new Course();
		return new ResponseEntity<>(course, HttpStatus.OK);
	}
	
	@GetMapping("/listLecturerByCourseId/{id}")
	public List<Lecturer> getLecturerByCourseId(@PathVariable("id") String courseId){
		Administrator admin = userSessionService.findAdminSession();
		if(admin== null) {
			return null;
		}
		
		List<Lecturer> lecturerlist = lecturerRepo.findLecturerByCourseId(courseId);
		return lecturerlist;
	}
	
	@GetMapping("/listAvilLecturerByCourseId/{id}")
	public List<Lecturer> getAvailableLecturerByCourseId(@PathVariable("id") String courseId){
		Administrator admin = userSessionService.findAdminSession();
		if(admin== null) {
			return null;
		}
		
		List<Lecturer> lecturerlist = lecturerRepo.findAvailLecturerByCourseId(courseId);
		return lecturerlist;
	}
	
	@PutMapping("/addLecturersByCourseId/{courseId}")
	public ResponseEntity<Course> addLecturerToCourse(@PathVariable("courseId") String courseCode, @RequestBody List<Lecturer> lecturers){
		Administrator admin = userSessionService.findAdminSession();
		if(admin== null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		

		Course course = courseRepo.findById(courseCode).get();
		if(course == null) {
			//No such course
			new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
		course.getCourseLecturers().clear();
		course.getCourseLecturers().addAll(lecturers);
		courseRepo.save(course);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
	@GetMapping("/getCourse/{courseId}")
	public ResponseEntity<Course> getCourseById(@PathVariable("courseId") String courseCode){
		Administrator admin = userSessionService.findAdminSession();
		if(admin== null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		
		Course course = courseRepo.findById(courseCode).get();
		if(course == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(course, HttpStatus.OK);
	}
	
	@PostMapping("/saveCourse")
	public ResponseEntity<Course> saveCourse(@RequestBody Course course){
		Administrator admin = userSessionService.findAdminSession();
		if(admin== null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		if(course.getCourseCode() == null || course.getCourseCode() == "") {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		Optional<Course> dbCourseOpt = courseRepo.findById(course.getCourseCode());
		try {
			if(!dbCourseOpt.isPresent()) {
				courseRepo.save(course);
			}
			else {
				Course dbCourse = dbCourseOpt.get();
				dbCourse.setCourseTitle(course.getCourseTitle());
				dbCourse.setCourseDescription(course.getCourseDescription());
				dbCourse.setCourseCapacity(course.getCourseCapacity());
				dbCourse.setCourseCredits(course.getCourseCredits());
				dbCourse.setCourseStatus(course.getCourseStatus());
				dbCourse.getCourseLecturers().clear();
				dbCourse.getCourseLecturers().addAll(course.getCourseLecturers());
				courseRepo.save(dbCourse);
			}
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		
		return new ResponseEntity<>(course, HttpStatus.OK);
	}

}
