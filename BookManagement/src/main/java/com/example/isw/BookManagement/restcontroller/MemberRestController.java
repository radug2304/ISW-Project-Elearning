package com.example.isw.BookManagement.restcontroller;

import com.example.isw.BookManagement.model.Member;
import com.example.isw.BookManagement.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/member")
public class MemberRestController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping(value = {"/", "/list"})
	public List<Member> all() {
		return memberService.getAllMembers();
	}
	
}
