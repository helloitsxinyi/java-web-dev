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
    // start and go to localhost:8080/h2-console
    // JDBC URL: see logs "Database available at 'jdbc:h2:mem:datajpa'"
    // now u can login using User ID and pw (as defined in config file) and do SQL queries to check tables and rows!


    // CommandLineRunner is just a quick way to test, better to write own tests
    @Bean
    CommandLineRunner runner() {
        return args -> {
            Customer c = new Customer("Tan", "SG");
            Customer c2 = new Customer("Lim", "SGP");
            Product p = new Product("Phone","mobile phone", 150.00);
            // flush = sync with underlying db.
            // save is just save.
            // but impt still need to commit.
            crepo.saveAndFlush(c);
            crepo.save(c2);
            prepo.save(p);
            ArrayList<Product> plist = (ArrayList) prepo.findAll();
            for (Product prod : plist) {
                System.out.println(prod.toString());
            }

            ArrayList<Customer> clist = (ArrayList) crepo.findAll();
            for (Customer cust: clist) {
                System.out.println(cust.toString());
            }

            ArrayList<Customer> cnames = (ArrayList) crepo.findCustomersByName("Tan");
            for (Customer cust: cnames) {
                System.out.println(cust.toString());
            }
        };
    }
}
