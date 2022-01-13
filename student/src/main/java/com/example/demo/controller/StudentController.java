package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Student;
import com.example.demo.repo.StudentRepository;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	// inject
	@Autowired
	StudentRepository srepo;
	
	@RequestMapping("/all")
	public String findAllStudents(Model model) {
		ArrayList<Student> slist = new ArrayList<Student>();
		slist.addAll(srepo.findAll());
		model.addAttribute("students", slist);
		return "slisting";
	}

}
