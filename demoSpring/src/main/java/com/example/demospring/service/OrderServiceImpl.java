package com.example.demospring.service;

import com.example.demospring.model.Address;
import com.example.demospring.model.Customer;
import com.example.demospring.model.LineItem;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Override
    public int InsertOrder(Customer c, Address sa, Address ba, HashSet<LineItem> li) {
        // check if c is valid
        // throw exc if fail. do at every step

        // check if sa and ba are valid (in DB)

        // check li are valid prods

        // insert

        return 0;
    }

    // refactor -> extract interface -> rename. select methods
}
