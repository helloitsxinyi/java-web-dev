package com.company;

import java.util.ArrayList;
import java.util.List;

public class ClubArrayList {
    private int membershipNumber = 0 ;

    private List<Member> members = new ArrayList<>();

    public ClubArrayList() {
    }

    public void showMembers() {
        // in 3rd element will encounter NPE because array is initialized with null objects
        // and 3rd element is null (only 1st and 2nd filled)
        for (Member mem : members) {
            mem.show();
        }
    }

    public List<Member> getMembers() {
        return members;
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
}
