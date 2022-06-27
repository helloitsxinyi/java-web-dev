package com.example.controllerdemo.book;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@ToString
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int isbn;
    @NotNull
    private String name;
    // only works w I/O validation dependency
    @NotEmpty
    private String description;
    @Max(1000)
    @Min(2)
    private Double price;

    public Book(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

}
