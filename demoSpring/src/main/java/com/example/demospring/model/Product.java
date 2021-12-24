package com.example.demospring.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="product")
@Data
@NoArgsConstructor
public class Product {
    // generation strategy is a MUST (identifier)
    // IDENTITY strategy gives control to connectivity framework(JPA). JPA designs.
    // AUTO is default settings of JPA
    // SEQUENCE done by entity side
    // TABLE done by r/s side.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String name;
    private String description;
    private Double price;

    public Product(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }


}
