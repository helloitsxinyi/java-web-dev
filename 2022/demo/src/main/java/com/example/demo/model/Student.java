package com.example.demo.model;
// why hibernate insert into lecturer ....values (default, ?, ?, ?) ??

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Student {
    @Id
    // default of strategy is AUTO (or whatever your hibernate default settings are.)
    // sequence - responsibility falls MySQL or any other DB you are talking to.
    // identity - responsibility falls on Spring or framework to generate PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String nickName;
    private Double cap;

    public Student() {
    }

    public Student(String name, String nickName, Double cap) {
        this.name = name;
        this.nickName = nickName;
        this.cap = cap;
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
        return id == student.id && Objects.equals(name, student.name) && Objects.equals(nickName, student.nickName) && Objects.equals(cap, student.cap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, nickName, cap);
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
