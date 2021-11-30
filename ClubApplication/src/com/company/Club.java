package com.company;

public class Club {
    private int membershipNumber = 0 ;

    public Member[] getMembers() {
        return members;
    }

    // declare and instantiate null array (cos using member objects)
    private Member[] members = new Member[20];

    public Club() {
    }

    public void addMember(String surname, String firstName, String secondName) {
        membershipNumber += 1;
        Member m = new Member(surname, firstName, secondName, membershipNumber);
        members[membershipNumber-1] = m;
    }

    public void showMembers() {
        // in 3rd element will encounter NPE because array is initialized with null objects
        // and 3rd element is null (only 1st and 2nd filled)
        for (Member mem : members) {
            mem.show();
        }
    }
}
