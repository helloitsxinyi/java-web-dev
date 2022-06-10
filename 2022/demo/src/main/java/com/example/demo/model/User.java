package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// why no entity?
@Data
@ToString
@NoArgsConstructor
public class User {
    private String username;
    private String password;

    // field constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username) {
        this.username = username;
    }
}
