package com.example.restdemo.repository;

import com.example.restdemo.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// path also can put /api
// collectionResourceRel -> collection name
// localhost:8080/customer GET POST etc -> everything is generated!
// diff is: if rest controller created at db level, CRUD is done for me!
// if create at controller level, you have to create CRUD yourself.
@RepositoryRestResource(collectionResourceRel = "customer", path = "customer")
public interface CustomerRestRepository extends JpaRepository<Customer, Integer> {

}
