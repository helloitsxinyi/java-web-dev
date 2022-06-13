package com.example.controllerdemo.book;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@ToString
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int isbn;
    private String name;
    private String description;
    private Double price;

    public Book(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
