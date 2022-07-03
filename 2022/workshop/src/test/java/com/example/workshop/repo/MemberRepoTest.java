package com.example.workshop.repo;

import com.example.workshop.WorkshopApplication;
import com.example.workshop.domain.Facility;
import com.example.workshop.domain.Member;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = WorkshopApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MemberRepoTest {
    @Autowired
    MemberRepository mrepo;

    @Test
    @Order(1)
    public void createMemberTest() {
        Member m = new Member("Bob", "Jones", "Tan");
        Member m2 = new Member("Bobby", "Jones", "Lim");
        Member m3 = new Member("Bob", "Jones", "Lim");
        Member m4 = new Member("Bobba", "Jones", "Too");
        List<Member> mlist = new ArrayList<>();
        mlist.add(m);
        mlist.add(m2);
        mlist.add(m3);
        mlist.add(m4);
        mrepo.saveAll(mlist);
        Assertions.assertNotNull(m);
        Assertions.assertNotNull(m2);
    }

    @Test
    @Order(2)
    public void getMemberTest() {
        Member m = mrepo.findById(2).get();
        Assertions.assertEquals("Bobby", m.getFirstName());
    }

    @Test
    @Order(3)
    public void getAllMembersTest() {
        List<Member> memberList = mrepo.findAll();
        Assertions.assertEquals(4, memberList.size());
    }

    @Test
    @Order(4)
    public void deleteFacilityTest() {
        mrepo.deleteById(2);
        List<Member> memberList = mrepo.findAll();
        Assertions.assertEquals(3, memberList.size());
    }

    @Test
    @Order(5)
    public void findMemberByFirstNameTest() {
        List<Member> memberList = mrepo.findMemberByFirstName("Bob");
        Assertions.assertEquals(2, memberList.size());
    }
}
