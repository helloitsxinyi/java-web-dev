package com.example.demospring.customertests;

import com.example.demospring.DemoSpringApplication;
import com.example.demospring.model.Customer;
import com.example.demospring.model.Product;
import com.example.demospring.repo.AddressRepo;
import com.example.demospring.repo.CustomerRepo;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
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
    public void testSomething() {
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

    @Test
    @Order(2)
    public void testReadByCustomerNameAndAddress() {
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

        Customer result = crepo.readCustomerByNameAndAddress("Ong");

        assertEquals(c2, result, "Test case failure");
    }

    @Test
    @Order(3)
    public void testReadAndSortByAddress() {
        crepo.deleteAll();
        Customer c = new Customer("Tan", "SG");
        Customer c1 = new Customer("Lim", "SG");
        Customer c2 = new Customer("Tan", "MY");
        Customer c3 = new Customer("And", "AU");
        ArrayList<Customer> clist = new ArrayList<>();
        clist.add(c);
        clist.add(c1);
        clist.add(c2);
        clist.add(c3);
        crepo.saveAllAndFlush(clist);

        // sort is a static class
        // ASC = ascending
        ArrayList<Customer> result = crepo.readCustomerSortedByName("Tan", Sort.by(Sort.Direction.ASC, "customerId"));

        assertEquals(result.size(), 2);
    }

    @Test
    void testReadByCustomer_Address_Street() {
        crepo.deleteAll();
        Customer c = new Customer("Tan", "SG");
        Customer c1 = new Customer("Lim", "SG P");
        Customer c2 = new Customer("Tan", "MY SG");
        Customer c3 = new Customer("And", "AU");
        Customer c4 = new Customer("Ann", "SG");
        Customer c5 = new Customer("Ong", "SG P");
        ArrayList<Customer> clist = new ArrayList<>();
        clist.add(c);
        clist.add(c1);
        clist.add(c2);
        clist.add(c3);
        clist.add(c4);
        clist.add(c5);
        crepo.saveAllAndFlush(clist);

        // must be exact match SG. no SG P or MY SG.
        ArrayList<Customer> result = crepo.readByAddress_StreetLike("SG");
        assertEquals(result.size(),2);

        ArrayList<Customer> result1 = crepo.readByAddress_StreetLike("%SG%");
        assertEquals(result1.size(),5);

        ArrayList<Customer> result2 = crepo.readByAddress_StreetLike("%SG");
        assertEquals(result2.size(),3);

        ArrayList<Customer> result3 = crepo.readByAddress_StreetLike("SG%");
        assertEquals(result3.size(),4);
    }
}
