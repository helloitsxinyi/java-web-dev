package com.company;

public class Person {
    private String surname;
    private String firstName;
    private String secondName;

    public Person(String surname, String firstName, String secondName) {
        this.surname = surname;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public void show() {
        String name = firstName;
        if (secondName != null) {
            name += " " + secondName;
        }
        name += " " + surname;
        System.out.println(name);
    }

    public String getSurname() {
        return surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }




}
