package com.company;

public class Facility {
    private String name;
    private String description;

    public Facility(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Facility(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        String text = name;
        if (description != null) {
            text += " (" + description + ")";
        }
        return text;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
