package com.kh.spring.member.controller;

import java.io.Writer;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kh.spring.LoggerTest;
import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
//@SessionAttributes({"loginMember", })
@SessionAttributes("loginMember")
@Slf4j // 이 어노테이션을 쓰면 log로 바로 Logger객체를 사용할 수 있다 
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	
	// 210625
	@Autowired
	private BCryptPasswordEncoder pwEncoder;
	
	
	// 210702
	@Autowired
	private BoardService boardService;
	
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(LoggerTest.class);
	
	
	

	@RequestMapping("/member/memberEnrollStart.do")
	public String memberEnrollStart() {
		return "member/memberEnroll";
		
	}
	
	// 회원등록 서비스
	@RequestMapping("/member/memberEnrollEnd.do")
	public String memberEnrollEnd(Member member, Model model) {
		
				// System.out.println("controller에서 테스트, 암호화전 member : " + member);
				// System.out.println("controller에서 테스트, 암호화전 : " + member.getPassword());
					
					// debug() 메소드의 매개변수로는 문자열만 넣을수 있는데 객체를 넣어야 한다면 아래와 같이 작성한다
					log.debug("파라미터값 {}",member);
					log.debug("암호화전 : {}", member.getPassword());
				
				
		// 비밀번호 단방향 암호화 적용 
		// BCryptPasswordEncoder객체의 encode(암호화할값) 메소드를 이용하면 된다
		member.setPassword(pwEncoder.encode(member.getPassword()));
				
				// System.out.println("controller에서 테스트, 암호화후 : " +  pwEncoder.encode(member.getPassword()));		
				
		
		int result = service.insertMember(member);
		
		model.addAttribute("msg", result > 0 ? "회원가입성공" : "회원가입실패");
		model.addAttribute("loc", "/");
		
		return "common/msg";
		
	}
	
	@RequestMapping("/member/memberLogin.do")
//	public String memberLogin(@RequestParam(value="userId") String userId, @RequestParam(value="password") String password) {
//	public String memberLogin(@RequestParam Map param, HttpSession session, Model model) {	
	public String selectMember(Member param, Model model, HttpSession session) {	
		
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
		// 두 비교값이 일치하면 true, 일치하지 않으면 false
			
		
		Member m = service.selectMember(param);

				// System.out.println("controller에서 테스트, m : " + m);
			
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
		
		
		
		// 로그아웃하면 redirect 해야한다 
		// "redirect:주소" 
		return "redirect:/"; // 메인화면으로 이동 ( url창의 주소가 바뀐다 ) 
	}
	
	
	
	// 210702
	// 기본 ajax 요청 응답하기
	@RequestMapping("/member/checkUserId.do")
	public void checkUserId(Member m , HttpServletResponse res, Writer writer) throws Exception{
		
		// 여기까지 요청이 들어오는지 확인
		log.info("{}", m); 
		log.info("{}", res); 
		
		
		Member result = service.selectMember(m);
		
		// res.getWriter().append( result != null ? "false" : "true");
		// writer.append( result != null ? "false" : "true");
		
		// gson 사용하기위해 pom.xml에 gson라이브러리 의존성등록
		new Gson().toJson(result, writer);
		
	}
	
	// jsonView를 통한 ajax 처리하기
	@RequestMapping("/member/checkIdJsonView.do")
	public ModelAndView jsonviewTest(Member m, ModelAndView mv) {
		
		Member result = service.selectMember(m);
		
		mv.addObject("isAble", result != null ? false : true);
		mv.addObject("su", 20);
		mv.addObject("name", "김태희포");
		mv.addObject("member", result);
		
		//viewName세팅시 등록한 JsonView bean의 id이름으로 설정해야함
		mv.setViewName("jsonView");
		
		return mv;
		
	}
	
	// @ResponseBody로 ajax 처리하기
	// 1. jackson data bind 라이브러리를 받아온다 
	// 2. Beanconfigration.xml에서 jackson클래스를 converter로 등록한다
	// 3. mapping된 메소드에 @@ResponseBody 어노테이션을 표시한다 ( 클래스선언부에도 작성가능 -> 클래스 안의 모든 메소드에 적용 ) 
	// 4. 원하는 데이터를 리턴값으로 설정한다
	@ResponseBody
	@RequestMapping("/member/responseBody.do")
	// public Member responseBody(Member m) {
	// public List<Board> responseBody(Member m) {
	public Map responseBody(Member m) {
		
		Member result = service.selectMember(m);
		
		List<Board> list = boardService.selectBoardList(1, 20);
		
		Map test = new HashedMap();
		test.put("member", result);
		test.put("boardList", list);
		
		// return result;
		// return list;
		return test;
	}
	
	
}
