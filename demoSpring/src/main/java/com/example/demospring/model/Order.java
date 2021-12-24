package com.example.demospring.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="orders")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @ManyToOne(optional = false)
    private Customer customer;

    @ManyToOne(optional = true)
    private Address billingAddress;

    @ManyToOne(optional = true)
    private Address shippingAddress;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="orderId")
    private Set<LineItem> lineItems = new HashSet<>();

    public Order(Customer customer, Address billingAddress, Address shippingAddress, Set<LineItem> lineItems) {
        this.customer = customer;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.lineItems = lineItems;
    }
}
