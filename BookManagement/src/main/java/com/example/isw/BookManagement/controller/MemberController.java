package com.example.isw.BookManagement.controller;

import com.example.isw.BookManagement.common.Constants;
import com.example.isw.BookManagement.model.Member;
import com.example.isw.BookManagement.service.ValidationService;
import com.example.isw.BookManagement.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/member")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private ValidationService validationService;
	
	@ModelAttribute(name = "memberTypes")
	public List<String> memberTypes() {
		return Constants.TYPES;
	}
	
	@RequestMapping(value = {"/", "/list"},  method = RequestMethod.GET)
	public String showMembersPage(Model model) {
		model.addAttribute("members", memberService.getAllMembers());
		return "/member/list";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addMemeberPage(Model model) {
		model.addAttribute("member", new Member());
		return "/member/form";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editMemeberPage(@PathVariable(name = "id") Integer id, Model model) {
		Member member = memberService.getMemberById( id );
		if( member != null ) {
			model.addAttribute("member", member);
			return "/member/form";
		} else {
			return "redirect:/member/add";
		}
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveMember(@Valid Member member, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
		String errEmail = validationService.validateEmail(member);

		if(!errEmail.isEmpty()){
			bindingResult.rejectValue("email","email",errEmail);
			return "/member/form";
		}

		if( bindingResult.hasErrors() ) {
			return "/member/form";
		}

		if( member.getId() == null ) {
			memberService.addNewMember(member);
			redirectAttributes.addFlashAttribute("successMsg", "'" + member.getFirstName()+" "+member.getMiddleName() + "' is added as a new member.");
			return "redirect:/member/add";
		} else {
			Member updatedMember = memberService.saveMember( member );
			redirectAttributes.addFlashAttribute("successMsg", "Changes for '" + member.getFirstName()+" "+member.getMiddleName() + "' are saved successfully. ");
			return "redirect:/member/edit/" + updatedMember.getId();
		}
	}
	
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public String removeMember(@PathVariable(name = "id") Integer id, Model model) {
		Member member = memberService.getMemberById( id );
		if( member != null ) {
			if( memberService.hasUsage(member) ) {
				model.addAttribute("memberInUse", true);
				return showMembersPage(model);
			} else {
				memberService.deleteMemberById(id);
			}
		}
		return "redirect:/member/list";
	}
	
	
	
	
}
