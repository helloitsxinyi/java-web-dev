package com.example.controllerdemo.controllers;

import com.example.controllerdemo.helper.Bag;
import com.example.controllerdemo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

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
    public String login(@ModelAttribute("user") User user, HttpSession session) {
        if(user.getUsername().equalsIgnoreCase("house")) {
            // valid session
            Bag p = new Bag(user);
            session.setAttribute("profile", p);
////          alternatively, this works too
////          then in thymeleaf bookform use session.profile.username
//            session.setAttribute("profile", user);
            return "books";
        }
        else return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "logout";
    }
}
