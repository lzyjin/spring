package com.kh.spring.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.kh.spring.LoggerTest;
import com.kh.spring.model.service.MemberService;
import com.kh.spring.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
//@SessionAttributes({"loginMember", })
@SessionAttributes("loginMember")
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	
	// 210625
	@Autowired
	private BCryptPasswordEncoder pwEncoder;
	
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(LoggerTest.class);
	
	
	

	@RequestMapping("/member/memberEnrollStart.do")
	public String memberEnrollStart() {
		return "member/memberEnroll";
		
	}
	
	@RequestMapping("/member/memberEnrollEnd.do")
	public String memberEnrollEnd(Member member, Model model) {
		
				// System.out.println("controller에서 테스트, 암호화전 member : " + member);
				// System.out.println("controller에서 테스트, 암호화전 : " + member.getPassword());
					log.debug("파라미터값 {}",member);
					log.debug("암호화전 : {}", member.getPassword());
				
				
		// 비밀번호 단방향 암호화 적용 
		// BCryptPasswordEncoder객체의 encode() 메소드를 이용하면 된다
		member.setPassword(pwEncoder.encode(member.getPassword()));
				
				// System.out.println("controller에서 테스트, 암호화후 : " +  pwEncoder.encode(member.getPassword()));		
				
		
		int result = service.insertMember(member);
		
		model.addAttribute("msg", result > 0 ? "회원가입성공" : "회원가입실패");
		model.addAttribute("loc", "index.jsp");
		
		return "common/msg";
		
	}
	
	@RequestMapping("/member/memberLogin.do")
//	public String memberLogin(@RequestParam(value="userId") String userId, @RequestParam(value="password") String password) {
//	public String memberLogin(@RequestParam Map param, HttpSession session, Model model) {	
	public String memberLogin(Member param, Model model, HttpSession session) {	
		
				// System.out.println("복호화 전 비번 : " + param.getPassword());
		
				   logger.debug((String)param.getPassword());
				   // 대신 log4j.xml에서 level이 info면 출력되지않는다
				   
				   // log4j.xml에서 레이아웃을 SimpleLayout으로 바꾸면
				   // DEBUG - 1234
				   
				   // 레이아웃을 HTMLLayout로 바꾸면
				   
				   /*
					   <tr>
					   <td>15184</td>
					   <td title="http-nio-9090-exec-3 thread">http-nio-9090-exec-3</td>
					   <td title="Level"><font color="#339933">DEBUG</font></td>
					   <td title="com.kh.spring.LoggerTest category">com.kh.spring.LoggerTest</td>
					   <td title="Message">1234</td>
					   </tr>
				    */
				   
				   // XML형식으로 하면
				   
				   /*
					   <log4j:event logger="com.kh.spring.LoggerTest" timestamp="1624588727180" level="DEBUG" thread="http-nio-9090-exec-6">
					   <log4j:message><![CDATA[1234]]></log4j:message>
					   </log4j:event>
				   */
				   
				   
		// 비교를 위한 메소드가 있다
		// BCryptPasswordEncoder객체의 matches() 메소드
		// matches(지금 입력받은 원본값(암호화전값), 암호화된 값) -> 반환값은 boolean
			
		
		Member m = service.memberLogin(param);

				System.out.println("controller에서 테스트, m : " + m);
			
		String msg = "로그인실패";
		
		
//		if(m != null) {
		if(m != null && pwEncoder.matches(param.getPassword(), m.getPassword())) {
		
			// session.setAttribute("loginMember", m);
			
			
			// session 대신 model을 이용해서 로그인에 대한 정보를 처리할 수 있다
			// 기본적으로 model은 request랑 같은 생명주기인데
			// 현재 클래스의 선언부에 @SessionAttributes(model의 key값) 어노테이션을 이용하면 
			// session의 생명주기와 동일하게 된다
			
			model.addAttribute("loginMember", m);
			
			msg = "로그인성공";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("loc", "/");
		
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
