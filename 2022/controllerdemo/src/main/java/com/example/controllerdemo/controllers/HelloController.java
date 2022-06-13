package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Calendar;
// rmb to import correct util class
import java.util.Date;
import java.util.stream.Stream;

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

    @GetMapping("/bootdetails")
    public String displayDetail(Model model ) {
//        Book b = (Book) findByIf(1).get
        return "";
    }
}
