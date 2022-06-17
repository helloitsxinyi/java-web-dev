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
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int facilityId;
    private String name;
    private String description;

    public Facility(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
