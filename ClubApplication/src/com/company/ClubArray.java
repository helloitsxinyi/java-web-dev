package com.company;

import java.util.Arrays;

public class ClubArray {
    private int membershipNumber = 0 ;

    // declare and instantiate null array (cos using member objects)
    private Member[] members = new Member[0];

    public ClubArray() {
    }

    public void showMembers() {
        // if just initialize array with size 20, and add only 2 elements through members[i] = elem;
        // in 3rd element will encounter NPE because array is initialized with null objects
        // and 3rd element is null (only 1st and 2nd filled)
        for (Member mem : members) {
            mem.show();
        }
    }

    public void addMember(String surname, String firstName, String secondName) {
        membershipNumber += 1;
        Member m = new Member(surname, firstName, secondName, membershipNumber);
        // now, array size may change due to removeMember.
        // therefore important to assign new length as length of previous members array + 1,
        // instead of just assigning the membership number.
        members = Arrays.copyOf(members, members.length+1);
        members[members.length-1] = m;
    }

    public void removeMember(int membershipNumber) {
        int index = 0;
        // to get the index of member to be removed,
        // as this always changes after addition/deletion
        for (int i = 0; i < members.length; i++) {
            if (members[i].getMemberNumber() == membershipNumber) {
                index = i;
            }
        }
        for (int i = index; i < members.length - 1; i++) {
            members[i] = members[i+1];
        }
        members = Arrays.copyOf(members, members.length - 1);
    }

    public Member[] getMembers() {
        return members;
    }
}