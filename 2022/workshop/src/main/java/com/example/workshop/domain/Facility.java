package com.example.workshop.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@ToString
@Table(name="Facility")
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer facilityId;
    private String name;
    private String description;

    public Facility(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Facility(int facilityId, String name, String description) {
        this.facilityId = facilityId;
        this.name = name;
        this.description = description;
    }
}
