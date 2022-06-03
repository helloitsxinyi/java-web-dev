package com.company;

public class Person {
    private String firstName;
    private String surname;
    private int age;
    private int numberKids;

    public Person(String firstName, String surname, int age, int numberKids) {
        this.firstName = firstName;
        this.surname = surname;
        this.age = age;
        this.numberKids = numberKids;
    }

    public int getKids() {
        return numberKids;
    }
}
