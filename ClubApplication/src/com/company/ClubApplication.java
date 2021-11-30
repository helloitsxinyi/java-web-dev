package com.company;

import java.util.List;

public class ClubApplication {
    public static void main(String[] args) {
        Person p1, p2, p3;
        Facility fac1, fac2, fac3;
        Member m1, m2, m3;
        Club c;


        p1 = new Person ("Edison", "Thomas", "Alva");
        p2 = new Person ("Baggio", "Roberto", null);
        p3 = new Person ("Webber", "Andrew", "Lloyd");

        fac1 = new Facility ("Main Hall", null);
        fac2 = new Facility ("Room1", "Conference Room on Level 2");
        fac3 = new Facility ("Room2", "Meeting Room on Level 3");

        m1 = new Member("Handelwood", "Hazel", "Anna", 234);

        c = new Club();

        c.addMember("Tan", "Ah", "Beng");
        c.addMember("Lint", "Linda", null);
        c.addMember("Hoon", "Jongwon", "Daniel");
        c.addMember("Brown", "Jerry", null);

//        Member[] members = c.getMembers();
        List<Member> members = c.getMembers();

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
        c.showMembers();
        c.removeMember(2);
        c.removeMember(3);
        c.showMembers();
        c.addMember("Lint", "Linda", null);
        c.showMembers();
        c.removeMember(4);
        c.showMembers();

        c.addFacility("Main Hall", null);
        c.addFacility("Room1", "Conference Room on Level 2");
        c.addFacility("Room2", "Meeting Room on Level 3");

        c.show();
    }
}
