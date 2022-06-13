package com.example.demo.model;
// why hibernate insert into lecturer ....values (default, ?, ?, ?) ??

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@ToString
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

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    // if no join column statement, spring will decide how to call the FK
    // place the @JoinColumn annotation to configure the name of the column in owning side. this maps to PK of other table.
    // who owns FK, put @JoinColumn.
    @JoinColumn(name="STU_LAP")
    private Laptop owns;

    public Student() {
        super();
    }

    public Student(String name, String nickName, Double cap) {
        super();
        this.name = name;
        this.nickName = nickName;
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
}
