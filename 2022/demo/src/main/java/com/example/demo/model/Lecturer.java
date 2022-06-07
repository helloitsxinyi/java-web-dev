package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@NoArgsConstructor
@Data
@ToString
@Entity
public class Lecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String nickname;
    private Double salary;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecturer lecturer = (Lecturer) o;
        return id == lecturer.id && Objects.equals(name, lecturer.name) && Objects.equals(nickname, lecturer.nickname) && Objects.equals(salary, lecturer.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, nickname, salary);
    }
}
