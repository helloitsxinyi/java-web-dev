package com.example.demospring.addresstests;

import com.example.demospring.DemoSpringApplication;
import com.example.demospring.model.Address;
import com.example.demospring.model.Customer;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoSpringApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class AddressUnitTests {

    @Autowired
    AddressRepo arepo;

    @Autowired
    CustomerRepo crepo;

//    each CRUD one test case
//    if create Customer, also create Address -> integrated test case

    @Test
    @Order(1)
    public void testCustomerUsingAddresses() {
        crepo.deleteAll();
        arepo.deleteAll();

        Address a = new Address("SG", "b", "c");
        Address a1 = new Address("SG", "d", "e");
        Address a2 = new Address("AU", "b", "c");
        ArrayList<Address> alist = new ArrayList<>();
        alist.add(a);
        alist.add(a1);
        alist.add(a2);
        arepo.saveAllAndFlush(alist);

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
        ArrayList<Customer> result = crepo.findCustomersByName("Tan");

        assertEquals(result.size(), 1);
    }
}
