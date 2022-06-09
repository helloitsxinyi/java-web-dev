//package com.example.UnitTests;
//
//import com.example.demo.model.Laptop;
//import com.example.demo.model.Student;
//import com.example.demo.repo.LaptopRepository;
//import com.example.demo.repo.StudentRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//@DataJpaTest
//public class StudentUnitTests {
//
//    @Autowired
//    // need to create srepo in tests also
//    StudentRepository srepo;
//
//    @Autowired
//    LaptopRepository lrepo;
//
//    @Test
//    @Order(1)
//    public void testCreateSL() {
//        Laptop l = new Laptop("TP", 2050.50);
//        Student s = new Student("student", "hi",5.0);
//
//        s.setOwns(l);
//        srepo.saveAndFlush(s);
//        Assertions.assertNotNull(s.getId);
//    }
//}
