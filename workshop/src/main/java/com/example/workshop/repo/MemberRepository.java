package com.example.workshop.repo;

import com.example.workshop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    @Query("select m from Member m where m.firstName = :fn")
    public ArrayList<Member> findMemberByFirstName(@Param("fn") String firstName);
}
