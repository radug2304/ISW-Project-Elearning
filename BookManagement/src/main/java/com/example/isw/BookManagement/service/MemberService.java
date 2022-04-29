package com.example.isw.BookManagement.service;

import com.example.isw.BookManagement.common.Constants;
import com.example.isw.BookManagement.model.Member;
import com.example.isw.BookManagement.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private IssueService issueService;

    public Long getCountTotal(){
        return memberRepository.count();
    }

    public Long getCountStudents(){
        return memberRepository.countByType(Constants.STUDENT);
    }

    public Long getCountOthers(){
        return memberRepository.countByType(Constants.OTHER);
    }

    public Long getCountParents(){
        return memberRepository.countByType(Constants.PARENT);
    }

    public List<Member> getAllMembers(){
        return memberRepository.findAllByOrderByFirstNameAscMiddleNameAscLastNameAsc();
    }

    public Member getMemberById(Integer id){
        return memberRepository.findById(id).get();
    }

    public Member addNewMember(Member member){
        member.setJoiningDate(new Date());
        return memberRepository.save(member);
    }

    public Member saveMember(Member member){
        return memberRepository.save(member);
    }

    public boolean hasUsage(Member member){
        return issueService.getCountByMember(member) > 0;
    }

    public void deleteMember(Member member){
        memberRepository.delete(member);
    }

    public void deleteMemberById(Integer id){
        memberRepository.deleteById(id);
    }
}
