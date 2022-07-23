package com.example.controllerdemo.helper;

import com.example.controllerdemo.model.User;

// like shopping bag.
// this class is designed to only remember the session
public class Bag {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bag(User user) {
        super();
        this.user = user;
    }

    public Bag() {
        super();
    }
}
