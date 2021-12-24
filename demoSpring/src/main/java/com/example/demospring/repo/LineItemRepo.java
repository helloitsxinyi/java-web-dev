package com.example.demospring.repo;

import com.example.demospring.model.LineItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineItemRepo extends JpaRepository<LineItem,Integer> {
}
