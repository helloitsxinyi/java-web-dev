package sg.edu.iss.caps.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.iss.caps.model.Administrator;
import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.CourseStudent;
import sg.edu.iss.caps.model.Student;
import sg.edu.iss.caps.model.User;
import sg.edu.iss.caps.service.CourseService;
import sg.edu.iss.caps.service.EmailService;
import sg.edu.iss.caps.service.StudentCourseService;
import sg.edu.iss.caps.service.StudentCourseServiceImpl;
import sg.edu.iss.caps.service.StudentServiceImpl;
import sg.edu.iss.caps.service.UserSessionService;
import sg.edu.iss.caps.util.MenuNavBarUtil;

@Controller
@RequestMapping("/manage/studentcourse")
public class AdminManageStudentCourseController {
	@Autowired
    StudentServiceImpl stuService;
	
	@Autowired
	StudentCourseServiceImpl scsImpl;
	
	@Autowired
    UserSessionService userSessionService;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
    StudentCourseService courseStudentService;
	
	@Autowired
    EmailService emailService;
	
	@GetMapping("/view")
    public String studentUnenrollView(Model model, @RequestParam(value="pageNo", required = false, defaultValue="1") Integer pageNo) {
		//View students list
		User user = userSessionService.findUserSession();
		if (user == null || !(user instanceof Administrator)) {
			return "redirect:/home";
		}
		MenuNavBarUtil.generateNavBar(user, model);
    	
//    	List<Student> studentActiveList = stuService.findAllActiveStudents();
//        model.addAttribute("studentList", studentActiveList);    	
        return findPaginatedStudent(pageNo,model);
    }
	
	public String findPaginatedStudent(int pageNo, Model model) {    	
    	int pageSize = 5;
    	
    	Page<Student> page = stuService.findPaginated(pageNo, pageSize);
    	List<Student> studentList = page.getContent();
    	
    	model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("studentList", studentList);
		
		return "student-list-view-unenroll";
    }
	
	@GetMapping("/enrolled-course-list/{id}")
    public String studentEnrolledCourses(Model model, @PathVariable("id") Integer id) {
		//View enrolled courses
		User user = userSessionService.findUserSession();
		if (user == null || !(user instanceof Administrator)) {
			return "redirect:/home";
		}
		MenuNavBarUtil.generateNavBar(user, model);
    	
        Student student = stuService.findStudentById(id);
        //List<Course> enrolledCourses = scsImpl.findEnrolledCourse(student);
        List<CourseStudent> enrolledCourseStuList = scsImpl.findCourseStudent(student);
        model.addAttribute("studentId", id);
		model.addAttribute("enrolledCourseStu", enrolledCourseStuList);
        return "course-list-view-unenroll";
    }
	
	@GetMapping("/unenroll/{id}/{coursecode}")
    public String unenrollCourse(Model model, @PathVariable("id") Integer id,@PathVariable("coursecode") String courseCode) {
		User user = userSessionService.findUserSession();
		if (user == null || !(user instanceof Administrator)) {
			return "redirect:/home";
		}
		MenuNavBarUtil.generateNavBar(user, model);
    	
    	scsImpl.unenrollStudentFromCourse(id, courseCode);
    	if(id != null)
    	{
    		return "redirect:/manage/studentcourse/enrolled-course-list/{id}";
    	}
    	return "redirect:/manage/studentcourse/view";
    }
	
	@GetMapping("/available-course-list/{id}")
    public String studentAvailableCourses(Model model, @PathVariable("id") Integer id, 
    		@RequestParam(value="pageNo", required = false, defaultValue="1") Integer pageNo) {
		//Get courses available
		
		User user = userSessionService.findUserSession();
		if (user == null || !(user instanceof Administrator)) {
			return "redirect:/home";
		}
		MenuNavBarUtil.generateNavBar(user, model);
    	
        Student student = stuService.findStudentById(id);
        model.addAttribute("studentId", id);
    	//List<Course> availableCourses = courseService.findAvailableCourseForStudent(student);
        //model.addAttribute("availableCourses",availableCourses);
    	
        return findPaginatedCourse(student, pageNo, model, null);
    }
	
	@GetMapping("/enroll/{id}/{coursecode}")
    public String enrollCourse(Model model, @PathVariable("id") Integer id,@PathVariable("coursecode") String courseCode) {
		User user = userSessionService.findUserSession();
		if (user == null || !(user instanceof Administrator)) {
			return "redirect:/home";
		}
		MenuNavBarUtil.generateNavBar(user, model);
    	
		Student student = stuService.findStudentById(id);
		courseStudentService.CreateNewCourseStudent(student, courseCode);
		
		emailService.SendCourseRegisteredEmail(student.getEmail(), String.join(" ", student.getFirstName(),student.getLastName()), courseCode);
		
        return "redirect:/manage/studentcourse/available-course-list/{id}";
    }
	
	@GetMapping("/search/{id}")
    public String enrollCourse(@RequestParam(value="searchCourse", required=false) String searchStr,
    		@PathVariable("id") Integer id, Model model,
    		@RequestParam(value="pageNo", required = false, defaultValue="1") Integer pageNo) {
		
		User user = userSessionService.findUserSession();
		if (user == null || !(user instanceof Administrator)) {
			return "redirect:/home";
		}
		MenuNavBarUtil.generateNavBar(user, model);
		
		if(searchStr == null || searchStr.equals("")) {
    		return "redirect:/manage/studentcourse/available-course-list/{id}";
    	}
		
		Student student = stuService.findStudentById(id);
		//List<Course> availableCourses = courseService.findSearchCourseForStudent(student, searchStr);
    	model.addAttribute("studentId", id);
    	//model.addAttribute("availableCourses",availableCourses);
    	model.addAttribute("searchStr",searchStr);
    	return findPaginatedCourse(student, pageNo, model, searchStr);
    }
	
	private String findPaginatedCourse(Student student, int pageNo, Model model, String searchStr) {
    	int pageSize = 5;
    	
    	//Page<Course> page = courseService.findPaginated(pageNo, pageSize);
    	//List<Course> courseList = page.getContent();
    	
    	//Find available courses
    	Page<Course> coursespage = courseService.findAvailableCourseForStudentPage(student, pageNo, pageSize, searchStr);
    	model.addAttribute("availableCourses",coursespage.getContent());
    	
    	if(coursespage != null) {
    		model.addAttribute("currentPage", pageNo);
    		model.addAttribute("totalPages", coursespage.getTotalPages());
    		model.addAttribute("totalItems", coursespage.getTotalElements());
    	}
    	
		return "available-course-list-view-enroll";
    }
}
