package com.example.demo;

import com.example.demo.model.Lecturer;
import com.example.demo.model.Student;
import com.example.demo.repo.LecturerRepository;
import com.example.demo.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // dependency injection
    @Autowired
    StudentRepository srepo;

    @Autowired
    LecturerRepository lrepo;

    @Bean
    // make a call to the model class via CommandLineRunner function
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("Inside the command line");
            Student s1 = new Student("Alan", "guy", 5.0);
            Student s2 = new Student("Mary", "girl", 5.0);
            Student s3 = new Student("Zuzu", "hamster", 5.0);
            Student s4 = new Student("Bean", "just a bean", 5.0);
            ArrayList<Student> solist = new ArrayList<Student>();
            solist.add(s1);
            solist.add(s2);
            solist.add(s3);
            solist.add(s4);
            srepo.saveAllAndFlush(solist);
            ArrayList<Student> srlist = srepo.funnyNameOne("hamster");
            for (Student s : srlist) {
                System.out.println(s.toString());
            }

            //for sout, lombok created is () instead of toString() using []
            Lecturer l1 = new Lecturer("Suria", "cranky", 100.00);
            Lecturer l2 = new Lecturer("Tin", "friendly", 100.00);
            Lecturer l3 = new Lecturer("Esther", "bossy", 100.00);
            Lecturer l4 = new Lecturer("Cher Wah", "sweet", 100.00);
            Lecturer l5 = new Lecturer("Yuen Kwan", "cheers", 100.00);

            // flush asks Entity Manager to persist.
//            srepo.saveAndFlush(s1);
            lrepo.saveAndFlush(l1);
            lrepo.saveAndFlush(l2);
            lrepo.saveAndFlush(l3);
            lrepo.saveAndFlush(l4);
            lrepo.saveAndFlush(l5);

            ArrayList<Lecturer> lList = lrepo.queryByNickname("cranky");
            for (Lecturer curr : lList) {
                System.out.println(curr.toString());
            }
        };
    }
}