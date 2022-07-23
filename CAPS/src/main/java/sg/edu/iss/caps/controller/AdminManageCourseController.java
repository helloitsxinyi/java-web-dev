package sg.edu.iss.caps.controller;

import java.util.List;

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
import sg.edu.iss.caps.model.Lecturer;
import sg.edu.iss.caps.model.User;
import sg.edu.iss.caps.service.CourseService;
import sg.edu.iss.caps.service.LecturerService;
import sg.edu.iss.caps.service.UserSessionService;
import sg.edu.iss.caps.util.MenuNavBarUtil;

@Controller
@RequestMapping("/manage/course")
public class AdminManageCourseController {
    
    @Autowired
    CourseService courseService;
    
    @Autowired
    LecturerService lecturerService;
    
    @Autowired
    UserSessionService userSessionService;
    
    @RequestMapping("/list")
    public String listCourses(Model model, @RequestParam(value="pageNo", required = false) Integer pageNo) {
    	Administrator user = userSessionService.findAdminSession();
    	if(user == null) {
    		return "redirect:/home";
    	}
    	MenuNavBarUtil.generateNavBar(user, model);

        List<Lecturer> lecturerList = lecturerService.findAllLecturers();
        model.addAttribute("lecturerList", lecturerList);

        return findPaginated(1, model);
    }
    
    @GetMapping("/delete/{courseId}")
    public String deleteCourse(Model model, @PathVariable("courseId") String courseId) {
    	Administrator user = userSessionService.findAdminSession();
    	if(user == null) {
    		return "redirect:/home";
    	}
    	MenuNavBarUtil.generateNavBar(user, model);
    	
        courseService.deleteCourse(courseId);
        return "redirect:/manage/course/list";
    }
    
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo, Model model) {
    	Administrator user = userSessionService.findAdminSession();
    	if(user == null) {
    		return "redirect:/home";
    	}
    	MenuNavBarUtil.generateNavBar(user, model);
    	int pageSize = 5;
    	
    	Page<Course> page = courseService.findPaginated(pageNo, pageSize);
    	List<Course> courseList = page.getContent();
    	
    	model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("courseList", courseList);
		
		return "list-courses";
    }
    
    /*
    @GetMapping("/create")
    public String createCoursePage(HttpSession session, Model model) {
    	User user = UserSessionUtil.findUser(session);
    	MenuNavBarUtil.generateNavBar(user, model);
    	
        Course c = new Course();
        
        List<Lecturer> lecturerList = lecturerService.findAllLecturers();
        model.addAttribute("course", c);
        model.addAttribute("lecturerList", lecturerList);
        return "course-form";
    }
	*/
    
    /*
    @PostMapping("/save")
    // validation tbc: courseCode
    public String saveCourse(@ModelAttribute("course") Course c, @RequestParam(value="courseLecturerAdd", required = false) Integer lecturerId, HttpSession session) {
        // if course present, get course object, otherwise create new course
    	
    	User user = UserSessionUtil.findUser(session);
    	
    	Lecturer l = lecturerService.findLecturerById(lecturerId);
    	courseService.SaveCourseAddLecturer(c, l);

        return "forward:/manage/course/list";
    }

	*/
//    // clarify purpose of editCoursePage
//    @GetMapping("/edit-page")
//    public String editCoursePage(Model model) {
//        return "edit-course";
//    }

    // clarify difference between this and editCoursePage
    

/*
    @GetMapping("/edit/{courseId}")
    public String editCourse(Model model, @PathVariable("courseId") String courseId, HttpSession session) {
    	User user = UserSessionUtil.findUser(session);
    	MenuNavBarUtil.generateNavBar(user, model);
    	
        Course c = courseService.findCourseById(courseId);
        List<Lecturer> lecturerList = lecturerService.findAllLecturers();
        model.addAttribute("course", c);
        model.addAttribute("lecturerList", lecturerList);
        return "course-form";
    }
*/
    //Comment out as the course should not be deleted. Instead should be closed
    
   

}
