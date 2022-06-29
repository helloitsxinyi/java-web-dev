package com.example.controllerdemo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@Entity
public class Cart {
    @Id
    private String id;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CartItem> cartItems;
    private Double grandTotal;
    //BigDecimal BigInteger
    public Cart(String id) {
        this.id = id;
    }

}