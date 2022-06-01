package com.company;

public class ClubApplication {

    public static void main(String[] args) {
        Person p = new Person("Tan","Ah Kow", "Benjamin");
        Person p2 = new Person("Tan","Ah Beng", null );

        p.show();
        p2.show();

        Facility f = new Facility("UTown", "place to find kat");
        Facility f2 = new Facility("ISS");

        f.show();
        f2.show();

        // both person and member ref type (LHS) work fine. why member ref type ok?
        Person m = new Member("Tan","Ah Hui", null, 123 );
        m.show();
    }
}
