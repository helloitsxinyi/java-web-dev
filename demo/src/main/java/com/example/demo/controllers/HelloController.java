package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Calendar;
// rmb to import correct util class
import java.util.Date;


@Controller
@RequestMapping("/hello")
public class HelloController {


    //    localhost:8080/hello/welcome
    @GetMapping("/welcome")
    public String welcomeMethod(Model model) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy");
        Date date = calendar.getTime();
        String today = date.toLocaleString();
//        String yesterday = calendar.add(DATE,-1);
        // push to Model class
        model.addAttribute("tday", today);
        // return the html page
        return "welcome";
    }

//    @GetMapping("/bookdetails")
//    public String displayDetail(Model model) {
//        Book b = (Book) brepo.findById(1).get();
//        model.addAttribute("book", b);
//        return "details";
//    }

    // using this PathVariable mapping, /hello/bookdetails will not work
    // instead, use /hello/bookdetails/1
    // the {var} must be same as @PathVariable("var")
//    @GetMapping("/bookdetails/{isbn}")
//    public String displayDetail(@PathVariable("isbn")String i1, Model model) {
////        impt to convert to i1
//        Book b = (Book) brepo.findById(Integer.parseInt(i1)).get();
//        model.addAttribute("book", b);
//        return "details";
//    }
}
