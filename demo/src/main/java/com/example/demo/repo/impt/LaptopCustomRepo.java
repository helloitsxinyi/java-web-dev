package com.example.demo.repo.impt;

import com.example.demo.model.Laptop;
import com.example.demo.repo.LaptopRepositoryEM;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;


public class LaptopCustomRepo implements LaptopRepositoryEM {
    @Autowired
    EntityManager em;

    public Laptop anyMethod() {
        //work with em here
        return null;
    }
}
