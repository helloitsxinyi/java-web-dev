package com.example.UnitTests;

import com.example.demo.DemoApplication;
import com.example.demo.model.Laptop;
import com.example.demo.model.Student;
import com.example.demo.repo.LaptopRepository;
import com.example.demo.repo.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DemoApplication.class)
@DataJpaTest
public class StudentUnitTests {

    @Autowired
    StudentRepository srepo;

    @Autowired
    LaptopRepository lrepo;

    @Test
    @Order(1)
    public void testCreateSL() {
        Laptop l = new Laptop("TP", 2050.50);
        Student s = new Student("student", "hi",5.0);

        s.setOwns(l);
        // should save even without injecting lrepo
        srepo.saveAndFlush(s);
        // if there is a PK, saveAndFlush is successful
        Assertions.assertNotNull(s.getId());
    }

    @Test
    @Order(2)
    public void testCreateSL2() {
        Laptop l = new Laptop("TP", 2050.50);
        lrepo.save(l);
        Student s = new Student("student", "hi",5.0);
        s.setOwns(l);
        srepo.saveAndFlush(s);
        System.out.println(s.toString());
        Assertions.assertNotNull(s.getId());

        // it comes as an arraylist. need to cast & get() first entry
        Student student = (Student) srepo.findById(s.getId()).get();
        System.out.println(student.getOwns().toString());

        // student will have record data
        // but laptop won't have relationship bc OTO fetch type in Student is lazy
    }

}
