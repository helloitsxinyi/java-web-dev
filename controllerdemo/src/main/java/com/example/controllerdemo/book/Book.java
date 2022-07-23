package com.example.controllerdemo.book;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
@Data
@ToString
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int isbn;
    @NotNull
    @Size(min=4, max=50, message = "{size.book.name.validation}")
    private String name;

    @NotEmpty
    private String description;
    @Max(1000)
    @Min(value=0, message="{min.book.price.validation}")
    @NotNull(message="{notNull.book.price.validation}")
    private Double price;

    public Book(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

}
