package com.example.demospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demospring.model.Address;

// ReqMapping is a more general form of specific HTTP mappings.
// use either Controller or REST controller
// use RestController if you want endpoints to be generated
// RestController for fullstack app (eg ReactJS), Controller for traditional app (eg Thymeleaf)
@Controller
@RequestMapping("/first")
public class SillyController {
	
	@PostMapping("/second")
	public String method1() {
		return "index";
	} 

	@GetMapping("/third")
	public String method2(Model model) {
		String message = "Some silly funny message";
		model.addAttribute("s", message);
		return "index";
	}
	
	// alt to GetMapping
	@RequestMapping(value = "/fourth", method = RequestMethod.GET) 
	public String method4() {
		return "index";
	}
	
	// alt to model.addAttribute (see "/third")
	@GetMapping("/fifth")
	public String method5(@ModelAttribute("a") Address add) {
		add.setStreet("street");
		add.setCity("SG");
		add.setCountry("Singapore");
		return "atest";
	}
}
