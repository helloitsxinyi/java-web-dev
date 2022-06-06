package com.example.demo.model;

import java.util.Objects;

public class Student {
    private int id;
    private String name;
    private String nickName;
    private Double cap;

    public Student(int id, String name, String nickName, Double cap) {
        this.id = id;
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
