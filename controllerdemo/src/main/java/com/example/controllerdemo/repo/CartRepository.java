package com.example.controllerdemo.repo;

import com.example.controllerdemo.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, String> {

}
