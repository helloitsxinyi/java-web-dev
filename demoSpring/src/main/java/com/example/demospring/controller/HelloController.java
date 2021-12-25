package com.example.demospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(Model model) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        Calendar cal = Calendar.getInstance();
        // create attr "today", add date inside.
        model.addAttribute("today", dateFormat.format(cal.getTime()));
        // return greetings.html, case sensitive. must use html
        return "greetings";
    }

}
