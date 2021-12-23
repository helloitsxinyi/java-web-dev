package com.example.demospring;

import com.example.demospring.model.Customer;
import com.example.demospring.model.Product;
import com.example.demospring.repo.CustomerRepo;
import com.example.demospring.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoSpringApplication {

    @Autowired
    ProductRepo prepo;

    @Autowired
    CustomerRepo crepo;

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringApplication.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            Customer c = new Customer("Tan", "SG");
            Product p = new Product("Phone","mobile phone", 150.00);
            crepo.save(c);
            prepo.save(p);
            ArrayList<Product> plist = (ArrayList) prepo.findAll();
            for (Product prod : plist) {
                System.out.println(prod.toString());
            }
            ArrayList<Customer> clist = (ArrayList) crepo.findAll();
            for (Customer cust: clist) {
                System.out.println(cust.toString());
            }
        };
    }
}
