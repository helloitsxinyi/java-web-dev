package com.example.restdemo.controller;

import com.example.restdemo.domain.Account;
import com.example.restdemo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// what artifact does this controller produce? any no of content ok.
@RequestMapping(path="/api/account", produces = "application/json")
// security tag to say who can access ur server side API endpoints.
// only allow web origins, only allow localhost8080.
@CrossOrigin("http://localhost:8080")
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/list")
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // dont need path -- POST to /api/account works alr
    @PostMapping
    // type account comes back
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        try {
            accountRepository.save(account);
            // params: body, status
            // if went through, object creation is successful. Give ok message
            return new ResponseEntity<>(account, HttpStatus.CREATED);
        } catch (Exception e) {
            // if fail, return null object. nothing was created.
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }
}
