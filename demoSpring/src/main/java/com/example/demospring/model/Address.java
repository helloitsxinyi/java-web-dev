package com.example.demospring.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="address")
@Data
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;
    private String street;
    private String city;
    private String country;
    // assume at least one customer. otherwise can use many to any
    @ManyToOne
    private Customer customer;

    public Address(String street, String city, String country) {
        this.street = street;
        this.city = city;
        this.country = country;
    }
}
