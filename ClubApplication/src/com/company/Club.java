package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Club {
    private int membershipNumber = 0 ;

    private List<Member> members = new ArrayList<>();
    private Map<String, Facility> facilities = new HashMap<>();

    public List<Member> getMembers() {
        return members;
    }

    public Map<String, Facility> getFacilities() {
        return facilities;
    }
    public Club() {
    }

    public void showMembers() {

        for (Member mem : members) {
            mem.show();
        }
    }

    public void addMember(String surname, String firstName, String secondName) {
        membershipNumber += 1;
        members.add(new Member(surname, firstName, secondName, membershipNumber));
    }

    public void removeMember(int membershipNumber) {
//        //simple iterator method
//        members.removeIf(member -> member.getMemberNumber() == membershipNumber);
        members.remove(findMember(membershipNumber));
    }

    public Member findMember(int membershipNumber) {
        for (Member mem: members) {
            if (mem.getMemberNumber() == membershipNumber) {
                return mem;
            }
        }
        return null;
    }

    public void addFacility(String name, String desc) {
        facilities.put(name, new Facility(name, desc));
    }

    public Facility getFacility(String name){
        return facilities.get(name);
    }

    public void removeFacility(String name) {
        facilities.remove(name);
    }

    public void showFacilities() {
        facilities.forEach((key,value)-> getFacility(key).show());
    }

    public void show() {
        System.out.println("Facilities: \n");
        showFacilities();
        System.out.println("Members: \n");
        showMembers();
    }
}
