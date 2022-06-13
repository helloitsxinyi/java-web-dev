package com.example.controllerdemo.controllers;

import com.example.controllerdemo.book.Book;
import com.example.controllerdemo.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Calendar;
// rmb to import correct util class
import java.util.Date;


@Controller
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    BookRepository brepo;

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

    @GetMapping("/bookdetails")
    public String displayDetail(Model model) {
        Book b = (Book) brepo.findById(1).get();
        model.addAttribute("book", b);
        return "details";
    }
}
