package com.spring.hellospring;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
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
				
				
				
		return "home"; // WEB-INF/views.home.jsp
	}
	
}
