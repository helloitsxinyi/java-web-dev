package com.company;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Club {
    private int membershipNumber = 0 ;

    public Member[] getMembers() {
        return members;
    }

    // declare and instantiate null array (cos using member objects)
    private Member[] members = new Member[0];

    public Club() {
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

    public void showMembers() {
        // in 3rd element will encounter NPE because array is initialized with null objects
        // and 3rd element is null (only 1st and 2nd filled)
        for (Member mem : members) {
            mem.show();
        }
    }

    public void removeMember(int membershipNumber) {
        int memNo = 0;
        // to get the index of member to be removed,
        // as this always changes after addition/deletion
        for (int i = 0; i < members.length; i++) {
            if (members[i].getMemberNumber() == membershipNumber) {
                memNo = i;
            }
        }
        for (int i = memNo; i < members.length - 1; i++) {
            members[i] = members[i+1];
        }
        members = Arrays.copyOf(members, members.length - 1);

    }
}
