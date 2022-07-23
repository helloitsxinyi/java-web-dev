package sg.edu.iss.caps.controller;

import java.util.List;

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

import sg.edu.iss.caps.model.Lecturer;
import sg.edu.iss.caps.model.User;
import sg.edu.iss.caps.service.LecturerService;
import sg.edu.iss.caps.service.UserSessionService;
import sg.edu.iss.caps.util.MenuNavBarUtil;

@Controller
@RequestMapping("/manage/lecturer") 
public class AdminManageLecturerController {
    
    @Autowired
    LecturerService lecturerService;
    
    @Autowired
	private UserSessionService userSessionService;
    
    @GetMapping("/create")
    public String loadLecturerForm(Model model) {
    	User user = userSessionService.findUserSession();
    	MenuNavBarUtil.generateNavBar(user, model);
    	
        Lecturer l = new Lecturer();
        model.addAttribute("lecturer",l);
        model.addAttribute("action","create");
        return "lecturer-form";
    }
    
    @PostMapping("/create")
    public String saveLecturerForm(@ModelAttribute("lecturer") @Valid Lecturer l, BindingResult bindingResult, Model model) {
    	User user = userSessionService.findUserSession();
    	MenuNavBarUtil.generateNavBar(user, model);

    	model.addAttribute("action","create");
    	if(lecturerService.checkIfEmailExist(l)) {
    		bindingResult.rejectValue("email","duplicate","Email already registered");
    	}
    	if(lecturerService.checkIfUsernameExist(l)) {
    		bindingResult.rejectValue("username","duplicate","Username already registered");
    	}
    	if(bindingResult.hasErrors()) {
			return "lecturer-form";
		}
    	lecturerService.saveLecturer(l);
        return "redirect:/manage/lecturer/list";
    }
    
    @GetMapping("/edit/{lecturerId}")
    public String loadEditLecturerForm(Model model, @PathVariable("lecturerId") Integer lecturerId) {
    	User user = userSessionService.findUserSession();
    	MenuNavBarUtil.generateNavBar(user, model);
    	
    	model.addAttribute("lecturer", lecturerService.findLecturerById(lecturerId));
    	 model.addAttribute("action","edit");
        return "lecturer-form";
    }
    
    @PostMapping("/edit/{lecturerId}")
    public String saveEditLecturerForm(@ModelAttribute("lecturer") @Valid Lecturer l, BindingResult bindingResult, Model model) {
    	User user = userSessionService.findUserSession();
    	MenuNavBarUtil.generateNavBar(user, model);
    	
    	model.addAttribute("action","edit");
		if(bindingResult.hasErrors()) {
			return "lecturer-form";
		}
		lecturerService.saveEditLecturer(l);
        model.addAttribute("action","edit");
        return "redirect:/manage/lecturer/list";
    }

    @RequestMapping("/list")
    public String listLecturers(Model model) {
    	User user = userSessionService.findUserSession();
    	MenuNavBarUtil.generateNavBar(user, model);
        
//        model.addAttribute("lecturerList", lecturerService.findAllActiveLecturers());
//        return "list-lecturers";
        return findPaginated(1, model);
    }

    @GetMapping("/delete/{lecturerId}")
    public String deleteLecturer(Model model, @PathVariable("lecturerId") Integer lecturerId) {
    	User user = userSessionService.findUserSession();
    	MenuNavBarUtil.generateNavBar(user, model);
    	
    	Lecturer l2 = lecturerService.findLecturerById(lecturerId);
    	lecturerService.deleteLecturer(l2);
        return "redirect:/manage/lecturer/list";
    }
    
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo, Model model) {
    	User user = userSessionService.findUserSession();
    	MenuNavBarUtil.generateNavBar(user, model);
    	int pageSize = 5;
    	
    	Page<Lecturer> page = lecturerService.findPaginated(pageNo, pageSize);
    	List<Lecturer> lecturerList = page.getContent();
    	
    	model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("lecturerList", lecturerList);
		
		return "list-lecturers";
    }
}
