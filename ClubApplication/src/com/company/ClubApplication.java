package com.company;

public class ClubApplication {

    public static void main(String[] args) {
//        Person p = new Person("Tan","Ah Kow", "Benjamin");
//        Person p2 = new Person("Tan","Ah Beng", null );
//
//        p.show();
//        p2.show();
//
//        Facility f = new Facility("UTown", "place to find kat");
//        Facility f2 = new Facility("ISS");
//
//        f.show();
//        f2.show();
//
//        // both person and member ref type (LHS) work fine. why member ref type ok?
//        Person m = new Member("Tan","Ah Hui", null, 123 );
//        m.show();

        // workshop3
        Club c = new Club();
        Member m = c.addMember("Brown", "Simon", "John");
        Member member1 = c.addMember("Edison", "Thomas", "Alva");
        Member member2 = c.addMember("Baggio", "Roberto", null);
        Member member3 = c.addMember("Webber", "Andrew", "Lloyd");
        Member member4 = c.addMember("Andy", "Ng", null);
        Member member5 = c.addMember("Aaron", "Tan", null);
        Member member6 = c.addMember("Daisy", "Teo", null);
        m.show();

        c.showMembers();

        c.removeMember(2);
        c.showMembers();

        System.out.println();
        c.removeMember(4);

        c.showMembers();

    }
}
