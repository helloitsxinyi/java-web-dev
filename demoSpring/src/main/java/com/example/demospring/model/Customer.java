package com.example.demospring.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="customer")
@Data
@NoArgsConstructor
// use comparable if sorting needs to based on natural order
public class Customer implements Comparable<Customer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    private String name;
    private String address;

    public Customer(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public int compareTo(Customer o) {
        if (this.customerId == o.getCustomerId())
            return 0;
        else
            return -1;

    }

}
