package iss.persistent;

import javax.persistence.*;

@Entity
public class Employee {
    @Id
    private int id;
    private String name;
    private long salary;

    public Employee() {
    }

    public Employee(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "[Employee: id=" + id + ", name=" + name + ", salary=" + salary + "]";
    }

}
