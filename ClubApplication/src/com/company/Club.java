package com.company;

public class Club {
    private int membershipNumber;

    public Member[] getMembers() {
        return members;
    }

    private Member[] members = new Member[20];

    public Club() {
    }

    public void addMember(String surname, String firstName, String secondName) {
        membershipNumber += 1;
        Member m = new Member(surname, firstName, secondName, membershipNumber);
        members[membershipNumber-1] = m;
    }
}
