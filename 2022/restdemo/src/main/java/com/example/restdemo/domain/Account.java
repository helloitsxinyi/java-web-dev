package com.example.restdemo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Account {
    @Id
    private String accountNo;
    private String name;
    private Double amount;

}
