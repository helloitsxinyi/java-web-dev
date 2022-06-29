package com.example.controllerdemo.controllers;

import com.example.controllerdemo.model.Cart;
import com.example.controllerdemo.repo.BookRepository;
import com.example.controllerdemo.repo.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/cart")
public class CartRestController {

    @Autowired
    private CartRepository crepo;

    @Autowired
    private BookRepository brepo;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void create(@RequestBody Cart cart) {
        crepo.save(cart);
    }

    @RequestMapping(value = "/{cartID}", method = RequestMethod.GET)
    public Cart read(@PathVariable(value="cartId") String cartId) {
        return crepo.findById(cartId).get();
    }

    @RequestMapping(value = "/{cartId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@PathVariable(value="cartId") String cartId, @RequestBody Cart cart) {
        cart.setId(cartId);
        crepo.save(cart);
    }
}
