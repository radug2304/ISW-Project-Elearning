package com.example.isw.BookManagement.service;

import com.example.isw.BookManagement.common.Constants;
import com.example.isw.BookManagement.model.Issue;
import com.example.isw.BookManagement.model.Member;
import com.example.isw.BookManagement.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class IssueService {
    @Autowired
    private IssueRepository issueRepository;

    public List<Issue> getAllIssues(){
        return issueRepository.findAll();
    }

    public Issue getIssueById(Integer id){
        return issueRepository.findById(id).get();
    }

    public List<Issue> getUnreturnedIssues(){
        return issueRepository.findByReturned(Constants.BOOK_NOT_RETURNED);
    }

    public Issue addNewIssue(Issue issue){
        issue.setIssueDate(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(issue.getIssueDate());
        calendar.add(Calendar.DATE, 30);
        issue.setExpectedReturnDate(calendar.getTime());
        issue.setReturned(Constants.BOOK_NOT_RETURNED);
        return issueRepository.save(issue);
    }

    public Issue saveIssue(Issue issue){
        return issueRepository.save(issue);
    }

    public Long getCountByMember(Member member){
        return issueRepository.countByMemberAndReturned(member,Constants.BOOK_NOT_RETURNED);
    }
}
