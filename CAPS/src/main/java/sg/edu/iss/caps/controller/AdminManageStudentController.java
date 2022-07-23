package sg.edu.iss.caps.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.model.Student;
import sg.edu.iss.caps.model.User;
import sg.edu.iss.caps.service.StudentService;
import sg.edu.iss.caps.service.UserSessionService;
import sg.edu.iss.caps.util.MenuNavBarUtil;
import sg.edu.iss.caps.util.UserSessionUtil;

@Controller
@RequestMapping("/manage/student")
public class AdminManageStudentController {

//    @Autowired
//    StudentRepository studentRepo;
    
    @Autowired
    StudentService stuService;
    
    @Autowired
    UserSessionService userSessionService;

    @GetMapping("/create")
    public String loadStudentForm(Model model) {
    	User user = userSessionService.findUserSession();
    	MenuNavBarUtil.generateNavBar(user, model);
    	
        Student s = new Student();
        model.addAttribute("student",s);
        model.addAttribute("action","create");
        return "student-form";
    }

    @PostMapping("/create")
    public String saveStudentForm(@ModelAttribute("student") @Valid Student s, BindingResult bindingResult, Model model) {
    	User user = userSessionService.findUserSession();
    	MenuNavBarUtil.generateNavBar(user, model);
    	
    	model.addAttribute("action","create");
    	if (bindingResult.hasErrors()) 
		{
			return "student-form";
		}
    	
    	if(s.getStudentId() != null)
    	{
    		stuService.editStudent(s);
    	}
    	else 
    	{
    		stuService.createStudent(s);
		}
        return "redirect:/manage/student/list";
    }

    @RequestMapping("/list")
    public String listStudents(Model model) {
    	User user = userSessionService.findUserSession();
    	MenuNavBarUtil.generateNavBar(user, model);

    	return findPaginated(1, model);
    }

//    @GetMapping("/list/{name}")
//    public String listStudentsByName(Model model, @PathVariable("name") String name) {
//    	User user = userSessionService.findUserSession();
//    	MenuNavBarUtil.generateNavBar(user, model);
//    	
//        List<Student> studentListByName = stuService.findAllStudentsByName(name);
//        model.addAttribute("studentList", studentListByName);
//        return "list-students";
//    }

    @GetMapping("/edit/{id}")
    public String loadEditStudentForm(Model model, @PathVariable("id") Integer id) {
    	User user = userSessionService.findUserSession();
    	MenuNavBarUtil.generateNavBar(user, model);
    	
    	Student studentById = stuService.findStudentById(id);
        model.addAttribute("student", studentById);
        model.addAttribute("action","edit");
        return "student-form";
    }
    
    @PostMapping("/edit/{id}")
    public String saveEditStudentForm(@ModelAttribute("student") @Valid Student s, BindingResult bindingResult, Model model) {
    	User user = userSessionService.findUserSession();
    	MenuNavBarUtil.generateNavBar(user, model);
    	
    	model.addAttribute("action","edit");
		if(bindingResult.hasErrors()) {
			return "student-form";
		}
		stuService.editStudent(s);
		model.addAttribute("action","edit");
        return "redirect:/manage/student/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(Model model, @PathVariable("id") Integer id) {
    	User user = userSessionService.findUserSession();
    	MenuNavBarUtil.generateNavBar(user, model);
    	
        stuService.deleteStudent(id);
        return "redirect:/manage/student/list";
    }
    
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo, Model model) {
    	User user = userSessionService.findUserSession();
    	MenuNavBarUtil.generateNavBar(user, model);
    	
    	int pageSize = 5;
    	
    	Page<Student> page = stuService.findPaginated(pageNo, pageSize);
    	List<Student> studentList = page.getContent();
    	
    	model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("studentList", studentList);
		
		return "list-students";
    }
}
