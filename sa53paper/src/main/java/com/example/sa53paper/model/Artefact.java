package com.example.sa53paper.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Artefact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer aId;
    private String name;

    @ManyToOne(targetEntity = Staff.class)
    private Staff staff;

    public Artefact(Integer aId, String name) {
        this.aId = aId;
        this.name = name;
    }
}
