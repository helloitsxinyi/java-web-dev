package com.example.demospring.repo;

import com.example.demospring.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// <what class to persist, identifier>
public interface ProductRepo extends JpaRepository<Product, Integer> {

}
