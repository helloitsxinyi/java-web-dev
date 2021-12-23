package com.example.demospring.repo;

import com.example.demospring.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    ArrayList<Customer> findCustomersByName(String name);
}
