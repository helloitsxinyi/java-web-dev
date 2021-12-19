package com.example.jpaexercise;

import javax.persistence.*;

@Entity
@Table(name="students")
public class Student {
    public Student() {}

    @Id
    private int id;

    @Column(name="matricNumber")
    private String matricNo;

    private String name;

}
