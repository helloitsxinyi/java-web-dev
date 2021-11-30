package com.company;

public class Member extends Person {
    private int memberNumber;

    public Member(String surname, String firstName, String secondName, int memberNumber) {
        super(surname, firstName, secondName);
        this.memberNumber = memberNumber;
    }

    @Override
    public String toString() {
        String text = super.toString() + " " + memberNumber;
        return text;
    }

    public int getMemberNumber() {
        return memberNumber;
    }

}
