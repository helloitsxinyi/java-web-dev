package com.example.controllerdemo.controllers;

import com.example.controllerdemo.book.Book;
import com.example.controllerdemo.repo.BookRepository;
import com.example.controllerdemo.validator.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    //ok to autowire first, haven't service classes
    @Autowired
    private BookRepository brepo;

    @Autowired
    private BookValidator bookValidator;

    // need to initbinder AND binding result for validator.
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(bookValidator);

    }

    // if return is a html page, string of file name will suffice.
    // if return type is another controller:
    // 1. forward to the controller -- within same application
    // 2. redirect -- to outside application. HTTP 3xx. Releases session data. -- redirect to another controller to another app

    @GetMapping("/add")
    // no "book" existing in scope, so cannot!!
//    public String loadForm(@ModelAttribute("book") Book b) {
    public String loadForm(Model model) {
        Book b = new Book();
        model.addAttribute("book", b);
        return "bookform";
    }

    @RequestMapping("/save")
    public String saveForm(@ModelAttribute("book") @Valid Book b, BindingResult bindingResult, Model model) {
        // always check if have errors if u have validator!!
        if (bindingResult.hasErrors()) {
            return "bookform";
        }
        brepo.save(b);
        // controller!! need requestMapping
        return "forward:/book/list";
    }

    // get mapping NOT post
    @GetMapping("/list")
    public String listPage(Model model) {
        List<Book> blist = brepo.findAll();
        // better to name books as bookslist or smt different to minimize confusion
        model.addAttribute("books", blist);
        for (Book b : blist) {
            System.out.println(b);
        }
        return "books";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("book", brepo.findById(id).get());
        return "bookform";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(Model model, @PathVariable("id") Integer id) {
        Book b = brepo.findById(id).get();
        brepo.delete(b);
        return "forward:/book/list";
    }


//
//    @RequestMapping("/list")
//    public String listPage(@ModelAttribute("books") List<Book> blist) {
//        blist = brepo.findAll();
//        return "forward:/book/list";
//    }

}
