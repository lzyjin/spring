package com.mvcboot.springboot.controller;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvcboot.springboot.model.service.MemberService;
import com.mvcboot.springboot.model.vo.Member;

@Controller
public class MvcController {
	
	@Autowired
	MemberService service;
	
	// XML방식으로하면 
	@Autowired
	private SqlSessionTemplate session;
	
	@RequestMapping("/")
	public String main(Model m) {
		
		List<Member> list = service.selectMemberAll();
		System.out.println(list);
		m.addAttribute("list", list);
		System.out.println("===================================");
		System.out.println(session.selectList("member.selectAll"));
		return "index";
	}
	
	@RequestMapping("/selectOneMember")
	public String selectOne(String userId, Model model) {
		
		Member m = service.selectOneMember(userId);
		model.addAttribute("m", m);
		
		return "searchMember";
	}
	
	
}
