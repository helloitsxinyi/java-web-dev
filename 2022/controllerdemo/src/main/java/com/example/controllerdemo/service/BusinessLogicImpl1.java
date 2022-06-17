package com.example.controllerdemo.service;

import org.springframework.stereotype.Service;

@Service
public class BusinessLogicImpl1 implements BusinessLogic {
    @Override
    public Double countAllBooks(int choice) {
        if (choice == 1.0) {
            return 10.0;
        }
        return 20.0;
    }
}
