package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	// whenever we go to /load, controller creates an empty instance student,
	// attaches it to Model, then goes to sform.
	@RequestMapping("/load")
	public String loadStudentForm(Model model) {
		Student student = new Student();
		// 1st arg-student object, 2nd arg-pass in empty data which is student instance.
		model.addAttribute("student", student);
		return "sform";
	}
	
	@PostMapping("/addstudent")
	public String addStudent(@ModelAttribute("student") Student student, Model model) {
		srepo.saveAndFlush(student);
		// go back to listing page
		return "all";
	}
	

}
