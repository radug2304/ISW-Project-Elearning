package com.example.isw.BookManagement.repository;

import com.example.isw.BookManagement.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    public List<Member> findAllByOrderByFirstNameAscMiddleNameAscLastNameAsc();
    public Long countByType(String type);
    public Member findByEmail(String email);
}
