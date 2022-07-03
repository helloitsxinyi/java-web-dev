package com.example.restdemo.repository;

import com.example.restdemo.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// path also can put /api
// collectionResourceRel -> collection name
@RepositoryRestResource(collectionResourceRel = "customer", path = "customer")
public interface CustomerRestRepository extends JpaRepository<Customer, Integer> {

}
