package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
		model.addAttribute("stdt", student);
		return "sform";
	}

	@PostMapping("/addstudent")
	// !!! QUESTION. why stu is ok? why no need stdt?
	// seems like: when we edit the form fields, and submit, 
	// action taken will be addStudent.
	// so the Student object in our form is passed into this controller as student
	
	// but why then still need @ModelAttribute?
	public String addStudent(@ModelAttribute("stooden") Student student, Model model) {
		srepo.saveAndFlush(student);
		// go back to listing page
		// we used PATH instead of html template!
		// can use slash if want to use controller!
		// CANNOT just use /all. must use /student/all
		// forward goes to a particular page

		// !!! Use forward because if go to slisting directly, won't pull all the
		// student data. !!!
		return "forward:/student/all";
	}

	@RequestMapping("/editload/{id}")
	public String loadEditStudentForm(@PathVariable("id") Integer id, Model model) {
		// findById returns ArrList. need to use .get to get the Student.
		Student s = srepo.findById(id).get();
		
		// add to ModelAttr as stdt. then go to sform.
		// 1st arg-student object, 2nd arg-pass in empty data which is student instance.
		// same to stdt because sform.html is expecting stdt!!
		model.addAttribute("stdt", s);
		return "sform";
	}

//	// seems to be similar to /addstudent, so commented out 
//	@PostMapping("/editstudent/{id}")
//	// use path variable bc taking in as path value
//	// need to add to Model too
//	// when data comes back, it is already in model. 
//	// get it based on saved ModelAttribute value in /editload
//	public String editStudent(@ModelAttribute("stooden") Student student, Model model ) {
//		srepo.saveAndFlush(student);
//		return "forward:/student/all";
//	}

	@RequestMapping("/deletestudent/{id}")
	public String deleteStudent(@PathVariable("id") Integer id, Model model) {
		Student s = srepo.findById(id).get();
		srepo.delete(s);
		return "forward:/student/all";
	}

}
