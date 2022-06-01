package com.company;

import java.util.ArrayList;

public class Club {
    private int memberNumber = 0;

    private Member[] memberArr = new Member[5];
//    private ArrayList<Member> memberList = new ArrayList<>();

    private Member addMember(String surname, String firstName, String secondName) {
        Member newMember = new Member(surname, firstName, secondName, memberNumber+=1);

        memberArr[memberNumber] = newMember;
        return newMember;
    }


}
