package com.example.demo.controllers;

import com.example.demo.controllers.book.Book;
import com.example.demo.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public class BookController {

    //ok to autowire first, haven't service classes
    @Autowired
    BookRepository brepo;

    @GetMapping("/add")
    public String loadForm(@ModelAttribute("book") Book b) {
        b = new Book();
        return "bookform";
    }

    @PostMapping("/save")
    public String saveForm(@ModelAttribute("book") Book b) {
        brepo.save(b);
//        what is the meaning of this format?
        return "forward:/book/list";
    }

    @RequestMapping("/list")
    public String listPage(@ModelAttribute("books") List<Book> blist) {
        blist = brepo.findAll();
        return "forward:/book/list";
    }

}
