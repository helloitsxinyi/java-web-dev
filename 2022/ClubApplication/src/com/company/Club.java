package com.company;

import java.util.ArrayList;

public class Club {

//    private int memberNumber;
//    private Member[] memberArr;
//
//    public Club() {
//        memberNumber = 1;
//        memberArr = new Member[5];
//    }
//
//    public Member addMember(String surname, String firstName, String secondName) {
//        ensureArrSize();
//        Member newMember = new Member(surname, firstName, secondName, memberNumber);
//
//        memberArr[memberNumber] = newMember;
//        memberNumber+=1;
//        return newMember;
//    }
//
//    private void ensureArrSize() {
//        if (memberNumber == memberArr.length) {
//            int newSize = memberArr.length * 2;
//            Member[] newMembers = new Member[newSize];
//
//            for (int i = 0; i < memberNumber; i++) {
//                newMembers[i] = memberArr[i];
//            }
//            memberArr = newMembers;
//        }
//    }
//
//    public void showMembers() {
//        for (Member m: memberArr) {
//            if (m != null) {
//                m.show();
//            }
//        }
//    }
//
//    public void removeMember(int memberNumber) {
//        // why if (membershipNumber >= 1 && membershipNumber <= this.membershipNumber - 1) ??
//        memberArr[memberNumber] = null;
//    }

    private int memberNumber = 1;
    ArrayList<Member> memberArrayList = new ArrayList<>();

    public Member addMember(String surname, String firstName, String secondName) {
        Member newMember = new Member(surname, firstName, secondName, memberNumber);
        memberArrayList.add(newMember);
        memberNumber+=1;
        return newMember;
    }

    public void removeMember(int memberNumber) {
        memberArrayList.remove(memberNumber);
    }

    public void showMembers() {
        for (Member m : memberArrayList) {
            m.show();
        }
    }

}
