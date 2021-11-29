package com.company;

public class ClubApplication {
    public static void main(String[] args) {
        Person p1, p2, p3, m1;
        Facility fac1, fac2, fac3;


        p1 = new Person ("Edison", "Thomas", "Alva");
        p2 = new Person ("Baggio", "Roberto", null);
        p3 = new Person ("Webber", "Andrew", "Lloyd");

        fac1 = new Facility ("Main Hall", null);
        fac2 = new Facility ("Room1", "Conference Room on Level 2");
        fac3 = new Facility ("Room2", "Meeting Room on Level 3");

        m1 = new Member("Tan", "Ah", "Beng", 123);

        System.out.println ("People:");
        p1.show();
        p2.show();
        p3.show();
        System.out.println ("\nFacilities:");
        fac1.show ();
        fac2.show ();
        fac3.show ();
        System.out.println ("\nMembers:");
        m1.show();


    }
}
