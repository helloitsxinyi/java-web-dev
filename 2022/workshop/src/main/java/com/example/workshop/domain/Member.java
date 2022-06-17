package com.example.workshop.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int memberId;
    private String firstName;
    private String secondName;
    private String lastName;
    private String username;
    private String password;

    public Member(String firstName, String secondName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }
}
