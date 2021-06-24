package com.kh.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.kh.spring.model.service.MemberService;
import com.kh.spring.model.vo.Member;

@Controller
//@SessionAttributes({"loginMember", })
@SessionAttributes("loginMember")
public class MemberController {
	
	@Autowired
	private MemberService service;

	@RequestMapping("/member/memberEnrollStart.do")
	public String memberEnrollStart() {
		return "member/memberEnroll";
		
	}
	
	@RequestMapping("/member/memberEnrollEnd.do")
	public String memberEnrollEnd(Member member, Model model) {
		
				System.out.println("controller에서 테스트, member : " + member);
		
		int result = service.insertMember(member);
		
		model.addAttribute("msg", result > 0 ? "회원가입성공" : "회원가입실패");
		model.addAttribute("loc", "index.jsp");
		
		return "common/msg";
		
	}
	
	@RequestMapping("/member/memberLogin.do")
//	public String memberLogin(@RequestParam(value="userId") String userId, @RequestParam(value="password") String password) {
//	public String memberLogin(@RequestParam Map param, HttpSession session, Model model) {	
	public String memberLogin(Member member, Model model, HttpSession session) {	
		
		Member m = service.memberLogin(member);

				System.out.println("controller에서 테스트, m : " + m);
		
		model.addAttribute("msg", m != null ? "로그인성공" : "로그인실패");
		model.addAttribute("loc", "index.jsp");
		
		if(m != null) {
			
			// session.setAttribute("loginMember", m);
			
			
			// session 대신 model을 이용해서 로그인에 대한 정보를 처리할 수 있다
			// 기본적으로 model은 request랑 같은 생명주기인데
			// 현재 클래스의 선언부에 @SessionAttributes(model의 key값) 어노테이션을 이용하면 
			// session의 생명주기와 동일하게 된다
			
			model.addAttribute("loginMember", m);
		}
		
		return "common/msg";
	}
	
	
	
	@RequestMapping("/member/logout.do")
	public String logout(HttpSession session, SessionStatus ss) {
		
		if(session != null) {
			session.invalidate(); 
		}
		
		
		
		// model로 추가한 sessionAttribute 데이터는 지워지지 않는다
		// model에서 추가한 객체는 SessionStatus를 이용해서 관리한다 
		
		if( !ss.isComplete() ) {
			ss.setComplete();
		}
		
		
		
		// redirect 해야한다 
		// "redirect:주소" 
		return "redirect:/";
	}
}
