package com.example.demo.model;
// why hibernate insert into lecturer ....values (default, ?, ?, ?) ??

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Student {
    @Id
    // default of strategy is AUTO (or whatever your hibernate default settings are.)
    // sequence - responsibility falls MySQL or any other DB you are talking to.
    // identity - responsibility falls on Spring or framework to generate PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String nickName;
    private Double cap;

    @OneToOne(cascade = {CascadeType.ALL})
    // if no join column statement, spring will decide how to call the FK
    // place the @JoinColumn annotation to configure the name of the column in owning side. this maps to PK of other table.
    // who owns FK, put @JoinColumn.
    @JoinColumn(name="owns_id")
    private Laptop owns;

    public Laptop getOwns() {
        return owns;
    }

//    @OneToOne
//    public Laptop getOwns() {
//        return owns;
//    }
//
//    public void setOwns(Laptop laptop) {
//        this.owns = laptop;
//    }

    public Student() {
        super();
    }

    public Student(String name, String nickName, Double cap) {
        super();
        this.name = name;
        this.nickName = nickName;
        this.cap = cap;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Double getCap() {
        return cap;
    }

    public void setCap(Double cap) {
        this.cap = cap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", cap=" + cap +
                '}';
    }
}
