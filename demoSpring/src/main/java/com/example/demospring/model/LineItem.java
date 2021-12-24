package com.example.demospring.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="lineitme")
@Data
@NoArgsConstructor
public class LineItem {
    @Id
    private Integer lineItemId;

    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private Double price;
    private Double amount;

    public LineItem(Product product, Double price, Double amount) {
        this.product = product;
        this.price = price;
        this.amount = amount;
    }
}
