package com.example.demospring.repo;

import com.example.demospring.model.Customer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.SortedMap;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    ArrayList<Customer> findCustomersByName(String name);

    @Query ("Select c from Customer c where c.address=:address and c.name = :name")
    // @Param to bind
    Customer readCustomerByNameAndAddress(@Param("name") String n, @Param("address") String a);

    @Query("select c from Customer c where c.address like :address")
    ArrayList<Customer> readAndSortByAddress(@Param("address") String a, Sort sort);
}
