package com.spring.hellospring.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController2 {

	
	@RequestMapping("/demo/demo2.do")
	public String demo() {
		
		// 화면전환용 
		return "demo/demo"; // return값을 viewResolver에게 보낸다. /WEB-INF/views/demo/demo.jsp 로 이동하게된다 
		// RequestDispatcher.forward(); 와 같다 
	}
	
	
	
	// controller객체의 메소드는 서블릿의 doGet/doPost 메소드의 역할을 한다
	// front에서 전송되는 모든 데이터는 controller 객체의 메소드에서 파라미터값으로 받는다 
	
	// 파라미터로 받을 수 있는 값 ( 장점 : 파라미터로 선언하면 자동으로 스프링 대입 )
	
	/*  매개변수를 선언만 하면 된다 
	 *  1. HttpServletRequest
	 *  2. HttpServletResponse 
	 *  3. HttpSession
	 *  4. java.util.Locale ( 지역 설정 정보 =  현재 서버의 위치에 대한 로컬값 ) 
	 *  5. InputStream / Reader 
	 *  6. OutputStream / Writer
	 *  
	 *  
	 *  
	 *  
	 *  Command 객체 : 파라미터값을 자동으로 객체로 받을 수 있다
	 *  
	 *  7. vo객체, Map : 파라미터값을 자동으로 대입해서 받는다 
	 *  8. Model : 데이터 공유 객체로 기본 request와 비슷하다
	 *  9. ModelAndView : 데이터 공유 객체 및 view에 대한 정보까지 같이 저장하는 객체
	 *  
	 *  
	 *  
	 *  특정 어노테이션을 선언해서 값을 받는 매개변수
	 *  
	 *  @RequestParam(value="parameter이름", 옵션..) 변수 = 변수 선언 
	 *  
	 * @RequestHeader(value="헤더 key값") 변수선언 : Header의 정보를 가져오는 것 
	 * 
	 * @CookieValue(value="쿠키key값") 변수선언 : Cookie 값 
	 * 
	 * 
	 * 
	 * 추가 메소드 선언부에 선언하는 어노테이션
	 * @PathVariable("값" : result방식으로 구현할 때 URL에 있는 데이터는 가져올 때 사용
	 * @ResponseBody = 클랑언트에게 응답할 때 메소드 리턴값을 JSON형태로 반환해주는 어노테이션
	 *  
	 */
	
	// 서블릿과 동일하게 사용하기
	@RequestMapping("/demo/demo1.do")
	public String demo1(HttpServletRequest req, HttpServletResponse res) {
		System.out.println(req.getParameter("devName"));
		System.out.println(res);
		return "";
		
	}

	
	
}
