package com.spring.hellospring;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.spring.hellospring.config.Student;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	
	// 210618
	@Autowired
	@Qualifier("emp1") // 이름으로 bean 지정
	private Employee e;
	// @Qualifier("emp1") 를 안쓰면 에러 (xml에 Employee타입이 2개 있는데 e가 id값과 잂치하지도 않고, 2개중 뭐랑 매치할지도 모르기 때문 )
	
	@Autowired
	private Student s;
	
	
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session, HttpServletResponse response) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		
		
				System.out.println(e);
				// servlet-context.xml에서 필드dept에 객체를 참조시킴
				// Employee [empNo=1, empName=김태희포, email=po@po.com, dept=Department(deptName=null, deptCount=0)]
				// Employee [empNo=1, empName=김태희포, email=po@po.com, dept=Department(deptName=D1, deptCount=100)]
		
				System.out.println(s);
				
				
				
				
		// 210623 
		// session에 값넣고 컨트롤러에서 확인하기 
		session.setAttribute("userId", "admin");
		
		
		// cookie 생성하기
		Cookie c = new Cookie("choco", "chip");
		
		c.setMaxAge(60*60*24);
		
		response.addCookie(c);		
		
				System.out.println("cookie :" + c);
		
		
				
				
//		return "home"; // WEB-INF/views.home.jsp
		
//		return "redirect:/";  // redirect 쓸땐 이렇게. 메인화면으로 이동한다는 뜻 
		
		return "index";
	}
	
}
