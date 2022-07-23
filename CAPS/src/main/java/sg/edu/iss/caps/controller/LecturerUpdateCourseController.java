package sg.edu.iss.caps.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import sg.edu.iss.caps.model.CourseStudent;
import sg.edu.iss.caps.model.Lecturer;
import sg.edu.iss.caps.model.Student;
import sg.edu.iss.caps.model.User;
import sg.edu.iss.caps.service.CourseService;
import sg.edu.iss.caps.service.LecturerCourseService;
import sg.edu.iss.caps.service.LecturerService;
import sg.edu.iss.caps.service.StudentCourseService;
import sg.edu.iss.caps.service.StudentService;
import sg.edu.iss.caps.service.UserSessionService;
import sg.edu.iss.caps.util.MenuNavBarUtil;
import sg.edu.iss.caps.util.UserSessionUtil;

@Controller
@RequestMapping("/lecturer")
public class LecturerUpdateCourseController {

    @Autowired
    LecturerCourseService lecturerCourseService;
    @Autowired
    LecturerService lecturerService;
    @Autowired
    CourseService courseService;
    @Autowired
    StudentService studentService;
    @Autowired
    StudentCourseService studentCourseService;
    
    @Autowired
    UserSessionService userSessionService;

    @RequestMapping("/list-courses")
    public String findLecturerAssignedCourses(Model model) {
    	Lecturer user = userSessionService.findLecturerSession();
    	MenuNavBarUtil.generateNavBar(user, model);

    	Lecturer l = lecturerService.findLecturerById(user.getLecturerId());
        model.addAttribute("teachCoursesList", lecturerCourseService.findLecturerAssignedCourses(l));
        model.addAttribute("showEnrollment",false);
        return "list-lecturer-courses";
    }
    
    @RequestMapping("/course-enrollment")
    public String findLecturerAssignedCoursesForEnrollment(Model model) {
    	Lecturer user = userSessionService.findLecturerSession();
    	MenuNavBarUtil.generateNavBar(user, model);

    	Lecturer l = lecturerService.findLecturerById(user.getLecturerId());
        model.addAttribute("teachCoursesList", lecturerCourseService.findLecturerAssignedCourses(l));
        model.addAttribute("showEnrollment",true);
        return "list-lecturer-courses";
    }
    
    @RequestMapping("/grade-course")
    public String findAssignedCoursesToGrade(Model model) {
    	Lecturer user = userSessionService.findLecturerSession();
    	MenuNavBarUtil.generateNavBar(user, model);

    	Lecturer l = lecturerService.findLecturerById(user.getLecturerId());
        model.addAttribute("teachCoursesList", lecturerCourseService.findLecturerAssignedCourses(l));
        model.addAttribute("studentPerformance",false);
        return "list-grade-course";
    }
    
    @RequestMapping("/grade-course/{courseCode}")
    public String findStudentsInCourse(Model model, @PathVariable("courseCode") String courseCode) {
    	Lecturer user = userSessionService.findLecturerSession();
    	MenuNavBarUtil.generateNavBar(user, model);
        
        model.addAttribute("courseStudentList", lecturerCourseService.findStudentsInCourse(courseCode));
        model.addAttribute("c", courseService.findCourseByCourseCode(courseCode));
        model.addAttribute("studentPerformance",false);
        return "list-courses-students";
    }
    
    @RequestMapping("/student-performance")
    public String viewStudentPerformance(Model model) {
    	Lecturer user = userSessionService.findLecturerSession();
    	MenuNavBarUtil.generateNavBar(user, model);

    	Lecturer l = lecturerService.findLecturerById(user.getLecturerId());
        model.addAttribute("teachCoursesList", lecturerCourseService.findLecturerAssignedCourses(l));
        model.addAttribute("studentPerformance",true);
        return "list-grade-course";
    }
    
    @RequestMapping("/student-performance/{courseCode}")
    public String viewStudentPerformanceInCourse(Model model, @PathVariable("courseCode") String courseCode) {
    	Lecturer user = userSessionService.findLecturerSession();
    	MenuNavBarUtil.generateNavBar(user, model);
        
        model.addAttribute("courseStudentList", lecturerCourseService.findStudentsInCourse(courseCode));
        model.addAttribute("c", courseService.findCourseByCourseCode(courseCode));
        model.addAttribute("studentPerformance",true);
        return "list-courses-students";
    }
    
    @RequestMapping("/student-profile/{studentId}")
    public String viewStudentProfile(Model model, @PathVariable("studentId") int studentId) {
    	Lecturer user = userSessionService.findLecturerSession();
    	MenuNavBarUtil.generateNavBar(user, model);

    	Student s = studentService.findStudentById(studentId);
        model.addAttribute("student", s);
        List<CourseStudent> courseGrades = studentCourseService.findStudentGrades(s);
        double gpa = studentCourseService.getGPA(courseGrades, s);
        if(Double.isNaN(gpa)) {
        	model.addAttribute("gpa", "Not Applicable");
        } else {
        	model.addAttribute("gpa", gpa);
        }
        
        return "lecturer-view-student-profile";
    }
    
    @GetMapping("/add-score/{courseStudentId}")
    public String loadAddScoreForm(Model model, @PathVariable("courseStudentId") Integer courseStudentId) {
    	User user = userSessionService.findUserSession();
    	MenuNavBarUtil.generateNavBar(user, model);

    	model.addAttribute("courseStudent", lecturerCourseService.findStudentGrade(courseStudentId));
        return "studentGrade-form";
    }
    
    @PostMapping("/add-score/{courseStudentId}")
    public String saveAddScoreForm(Model model, @ModelAttribute("courseStudent") @Valid CourseStudent cs, BindingResult bindingResult, int score) {
    	User user = userSessionService.findUserSession();
    	MenuNavBarUtil.generateNavBar(user, model);
    	if(bindingResult.hasErrors()) {
			return "studentGrade-form";
		}
    	CourseStudent cs2 = lecturerCourseService.findStudentGrade(cs.getCourseStudentId());
    	lecturerCourseService.saveStudentGrade(cs2, score);
        return "redirect:/lecturer/grade-course/" + cs2.getCourse().getCourseCode();
    }
    
//  @RequestMapping("/list-course-students/{courseCode}")
//  public String findStudentsInCourse(HttpSession session, Model model, @PathVariable("courseCode") String courseCode) {
//  	User user = UserSessionService.findUser(session);
//  	MenuNavBarUtil.generateNavBar(user, model);
//      
//      model.addAttribute("courseStudentList", lecturerCourseService.findStudentsInCourse(courseCode));
//      model.addAttribute("c", courseService.findCourseByCourseCode(courseCode));
//      return "list-courses-students";
//  }
    
//    @PostMapping("/save")
//    public String saveCourseStudentForm(@ModelAttribute("courseStudent")  CourseStudent cs, HttpSession session) {
//    	User user = UserSessionService.findUser(session);
//    	
//    	CourseStudent cs2 = courseStudentRepo.findById(cs.getCourseStudentId()).get();
//    		cs2.setCourseStudentId(cs.getCourseStudentId());
//    		cs2.setCourse(cs.getCourse());
//    		cs2.setStudent(cs.getStudent());
//    		courseStudentRepo.save(cs2);
//        return "forward:/course-student/list";
//    }
        
//    @GetMapping("/{id}/list")
//    public String listCourseStudent(Model model, @PathVariable("id") Integer courseStudentId) {
//        CourseStudent courseStudent = courseStudentRepo.findById(courseStudentId).get();
//        model.addAttribute("courseStudent", courseStudent);
//        return "list-coursestudents";
//    }
//
//    @GetMapping("/{id}/view-grade")
//    public String viewGrade(Model model, @PathVariable("id") Integer courseStudentId) {
//        CourseStudent courseStudent = courseStudentRepo.findById(courseStudentId).get();
//        model.addAttribute("courseStudent", courseStudent);
//        return "coursestudent";
//    }


}
