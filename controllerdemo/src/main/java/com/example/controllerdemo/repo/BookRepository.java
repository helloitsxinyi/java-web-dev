package com.example.controllerdemo.repo;

import com.example.controllerdemo.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

}