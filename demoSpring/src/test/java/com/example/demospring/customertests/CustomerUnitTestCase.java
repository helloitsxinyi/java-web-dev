package com.example.demospring.customertests;

import com.example.demospring.DemoSpringApplication;
import com.example.demospring.model.Customer;
import com.example.demospring.model.Product;
import com.example.demospring.repo.CustomerRepo;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoSpringApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class CustomerUnitTestCase {

    // In Spring, the objects that form the backbone of your application and that are managed by the Spring IoC container
    // are called beans. A bean is an object that is instantiated, assembled, and otherwise managed by a Spring IoC container.

    // Autowiring feature of spring framework enables you to inject the object dependency implicitly.
    // It internally uses setter or constructor injection.
    // Autowiring can't be used to inject primitive and string values. It works with reference only.
    @Autowired
    CustomerRepo crepo;
    @Test
    // first to be executed. must include order!
    @Order(1)
    void testSomething() {
        // will not pass on the first time, because previously in CmdLineRunner we alr added 2! so total will be 6, not 4.
        // need to go to H2 and drop rows
        // alt, can use deleteAll.
        crepo.deleteAll();
        Customer c = new Customer("Tan", "SG");
        Customer c1 = new Customer("Lim", "SGP");
        Customer c2 = new Customer("Ong", "MY");
        Customer c3 = new Customer("And", "AU");
        ArrayList<Customer> clist = new ArrayList<>();
        clist.add(c);
        clist.add(c1);
        clist.add(c2);
        clist.add(c3);

        crepo.saveAllAndFlush(clist);

        ArrayList<Customer> rlist = (ArrayList) crepo.findAll();
        for (Customer cust: clist) {
            System.out.println(cust.toString());
        }

        assertEquals(rlist.size(),4);
    }


}
