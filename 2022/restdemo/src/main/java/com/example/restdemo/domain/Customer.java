package com.example.restdemo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer custId;
    private String name;
    private Double balance;


}
