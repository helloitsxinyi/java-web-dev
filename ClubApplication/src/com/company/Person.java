package com.company;

public class Person {
    private String firstName;
    private String secondName;
    private String surname;

    public Person(String surname, String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getSurname() {
        return surname;
    }

    public void show() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        String name = firstName;
        if (secondName != null) {
            name += " " + secondName;
        }
        name += " " + surname;
        return name;
    }
}
