package com.company;

public class Member extends Person {
    private int memberNumber;

    public Member(String surname, String firstName, String secondName, int memberNumber) {
        super(surname, firstName, secondName);
        this.memberNumber = memberNumber;
    }

    public int getMemberNumber() {
        return memberNumber;
    }

    @Override
    public String toString() {
        String text = super.toString() + " " + memberNumber;
        return text;
    }

}
