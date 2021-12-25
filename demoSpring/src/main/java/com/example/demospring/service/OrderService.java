package com.example.demospring.service;

import com.example.demospring.model.Address;
import com.example.demospring.model.Customer;
import com.example.demospring.model.LineItem;

import java.util.HashSet;

public interface OrderService {
    int InsertOrder(Customer c, Address sa, Address ba, HashSet<LineItem> li);
}
