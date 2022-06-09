package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String model;
    private Double price;
    // the table with the mappedBy is the secondary table.
    @OneToOne(mappedBy = "owns")
    private Student student;

    public Laptop(String model, Double price) {
        this.model = model;
        this.price = price;
    }
//
//    @OneToOne(mappedBy = "owns")
//    private Student belongsTo() {
//        return
//    }
}
