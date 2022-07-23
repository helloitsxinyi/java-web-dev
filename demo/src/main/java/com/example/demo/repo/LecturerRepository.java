package com.example.demo.repo;

import com.example.demo.model.Lecturer;
import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

// id use wrapper is better because can work with objects. int abit hard to use
public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {
    public ArrayList<Lecturer> queryByNickname(String s);


}