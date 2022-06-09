package com.example.demo.repo;
// project runs on console, got
import com.example.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

@SpringBootApplication
public interface StudentRepository extends JpaRepository<Student, Integer> {

    // write a query
    // write not on a db, but on STUDENT
    // important for student to be CAPS because u are querying class name
    // ?1 -> replace first argument with input

    // // default style
    // @Query("from Student s where s.nickName=?1")
    // public ArrayList<Student> funnyNameOne(String nickName);

    //named query style
    @Query("from Student s where s.nickName = :nn")
    public ArrayList<Student> funnyNameOne(@Param("nn") String nickName);

    // // use native true if want to query in your own db eg heidi etc
    // @Query(value = "insert sql query here", nativeQuery = true) {

//    }
}