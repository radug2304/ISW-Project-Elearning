package com.example.isw.BookManagement.repository;

import com.example.isw.BookManagement.model.Issue;
import com.example.isw.BookManagement.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Integer> {
    public List<Issue> findByReturned(Integer returned);
    public Long countByMemberAndReturned(Member member, Integer returned);
}
