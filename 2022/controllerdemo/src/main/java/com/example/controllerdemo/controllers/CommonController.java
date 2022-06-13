package com.example.demo.controllers;


import com.example.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

// IMPT!!!!
@Controller
public class CommonController {

    // write methods.
    @GetMapping("/login")
    public String login(Model model) {
        // create empty user model
        User u = new User();
        model.addAttribute("user", u);
        return "login";
    }

    @RequestMapping("/authenticate")
    // object comes in as user object
    public String login(@ModelAttribute("user") User user) {
        if(user.getUsername().equalsIgnoreCase("house")) {
            return "products";
        }
        else return "login";
    }
}
