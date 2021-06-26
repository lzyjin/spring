package com.spring.hellospring.demo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.hellospring.demo.model.service.DemoService;
import com.spring.hellospring.demo.model.vo.Dev;

@Controller
public class DemoController2 {

	
	@Autowired
	private DemoService service;
	
	
	@RequestMapping("/demo/demo2.do")
	public String demo() {
		
		// 화면전환용 
		return "demo/demo"; // return값을 viewResolver에게 보낸다. /WEB-INF/views/demo/demo.jsp 로 이동하게된다 
		// RequestDispatcher.forward(); 와 같다 
	}
	
	
	
	
	
	
	// controller객체의 메소드는 서블릿의 doGet/doPost 메소드의 역할을 한다
	// front에서 전송되는 모든 데이터는 controller 객체의 메소드에서 파라미터값으로 받는다 
	
	// 파라미터로 받을 수 있는 값 ( 장점 : 파라미터로 선언하면 자동으로 스프링 대입 )
	
	/*  
	 *  -- 매개변수를 선언만 하면 된다 --
	 *  
	 *  1. HttpServletRequest
	 *  2. HttpServletResponse 
	 *  3. HttpSession
	 *  4. java.util.Locale ( 지역 설정 정보 =  현재 서버의 위치에 대한 로컬값 ) 
	 *  5. InputStream, Reader 
	 *  6. OutputStream,  Writer
	 *  
	 *  
	 *  
	 *  -- Command 객체 : 파라미터값을 자동으로 객체로 받을 수 있다 -- 
	 *  
	 *  7. vo객체, Map : 파라미터값을 자동으로 대입해서 받는다 
	 *  8. Model : 데이터 공유 객체로 기본 request와 비슷하다
	 *  9. ModelAndView : 데이터 공유 객체 및 view에 대한 정보까지 같이 저장하는 객체
	 *  
	 *  
	 *  
	 *  -- 특정 어노테이션을 선언해서 값을 받는 매개변수 --
	 *  
	 *  @RequestParam(value="parameter이름", 옵션...) :  변수 선언 
	 *  @RequestHeader(value="헤더의 key값") : 변수선언 (Header의 정보를 가져오는 것 )
	 *  @CookieValue(value="쿠키의 key값") : 변수선언 (Cookie 값)
	 * 
	 * 
	 * 
	 *  -- 추가 메소드 선언부에 선언하는 어노테이션 -- 
	 *  
	 *  @PathVariable("값") : resultful방식으로 구현할 때 URL에 있는 데이터는 가져올 때 사용
	 *  @ResponseBody = 클라이언트에게 응답할 때 메소드의 리턴값을 JSON형태로 반환해주는 어노테이션 ( 잭슨 라이브러리 이용 )
	 *  
	 */
	
	
	
	
	
	
	
	
	
	
	// 서블릿과 동일한 방식으로 사용하기
	@RequestMapping("/demo/demo1.do")
	public String demo1(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		
		
		
//			System.out.println(req.getParameter("devName"));
//			System.out.println(res);
		
//		    return "";
		
		
		
		// 210623 
		
		Dev dev = new Dev();
		
		dev.setDevName(req.getParameter("devName"));
		dev.setDevAge(Integer.parseInt(req.getParameter("devAge")));
		dev.setDevEmail(req.getParameter("devEmail"));
		dev.setDevGender(req.getParameter("devGender"));
		dev.setDevLang(req.getParameterValues("devLang"));
		
		req.setAttribute("dev", dev);
		
		
		
		// session을 사용하고 싶으면 매개변수로 HttpSession session를 받고 바로 사용가능 
		
		// HomeController에서 저장한 session 확인하기 
		System.out.println("session 값 : " + session.getAttribute("userId"));
		
		// index.jsp를 views폴더 안으로 옮김 
		
		
		
		return "demo/demoResult";

		
	}
	
	
	
	
	
	
	
	
	
	// @RequestParam 어노테이션 이용하기 
	
	// 클라이언트가 전송하는 값을 1:1로 매개변수로 바로 받을 수 있다 
	
	// @RequestParam으로 가져오면 디폴트로 required가 true다 ( 라디오버튼 선택안하면 에러 ) 
	// 없어도 되는 값으로 처리하려면 required = false 로 설정한다
	
	// 나이같은 경우 값이 없으면 null이 들어오는데 int타입으로 받으니까 에러난다 
	// -> 해결방법 : defaultValue = "1" 값이 없을때 설정할 디폴트값
	// 배열도 같은개념
	
	@RequestMapping("/demo/demoTwo.do")
//	public String demoTwo(@RequestParam(value="devName") String devName, 
//							@RequestParam(value="devAge", required = false, defaultValue = "1") int devAge,
//							@RequestParam(value="devEmail") String devEmail,
//							@RequestParam(value="devGender", required = false) String devGender,
//							@RequestParam(value="devLang") String[] devLang, 
//							Model m) {
	
	// 모든 값이 입력되어있다는 전제 하에 이렇게 작성 가능
	public String demoTwo(String devName, int devAge, String devEmail, String devGender, String[] devLang, Model m) {	
		
				System.out.println(devName + ", " + devAge + ", " + devEmail + ", " + devGender);
				
		Dev d = Dev.builder().devName(devName).devAge(devAge).devEmail(devEmail).
					  devGender(devGender).devLang(devLang).build();
				
		// request를 굳이 안해도 model이라는 객체를 이용할 수 있다 
		// 매개변수로 받으면 바로 사용 가능 
		
		// Model : 데이터 공유 객체
		// Model 객체를 이용해서 서버데이터 전송하기
		// addAttribute() : 데이터를 저장할 때 key/value 형식으로 저장 
	
		m.addAttribute("dev", d);
		// request와 생존주기가 동일하다 
		
		
		
		
		for(String dl : devLang) {
			System.out.print(dl + ",");
		}
	
		
		return "demo/demoResult";
	}

	
	
	
	// Command 객체로 파라미터 받아오기
	
	// Command 객체로 지정된 객체의 멤버변수와 파라미터의 key값이 일치해야 대입할 수 있다
	// 자동으로 default생성자로 생성 후 setter로 값을 대입한다 : 메소드 명명규칙을 준수해야한다 
	
	// 주의할 점 
	// 기본자료형을 제외한 자료형(객체자료형)이 있으면 대입이 제한된다 
	@RequestMapping("/demo/demo3.do")
	public String demo3(Dev dev, Model m) {
		
		m.addAttribute("dev", dev);
		
		
		return "demo/demoResult";
	}
	
	
	
	
	
	
	// Map객체를 이용해서 파라미터 처리하기
	// 기본자료형(배열x)
	@RequestMapping("/demo/demo4.do")
	public String demo4(@RequestParam Map param, Model m, String[] devLang) {
		
				System.out.println(param); 
				// {devName=양호준, devAge=87, devEmail=yoo@yoo.naver.com, devGender=F, devLang=Java}
				
		for(String d : devLang) {
			System.out.println(d);
			// Java
			// C
		}
		
		
		param.put("devLang", devLang);
		m.addAttribute("dev", param);
		
	
		return "demo/demoResult";
	}
	
	
	
	
	
	
	// 추가데이터를 매개변수로 받기 ( header정보와 cookie정보 )
	@RequestMapping("/demo/demo5.do")
	public String demo5(@RequestHeader(value="User-agent") String userAgent,
						@RequestHeader(value="Referer") String prevPage, 
						@CookieValue(value="choco", required = false) String snack) {
		
				System.out.println(userAgent); // Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36
				System.out.println(prevPage); // http://localhost:9090/hellospring/demo/demo2.do ( 주소창에 찍혀있는 url ) 
				System.out.println(snack); // chip
		
		
		return "demo/demo";
	}
	
	
	
	
	
	
	
	@RequestMapping("/demo/insertDev.do")
	public String insertDev(Dev dev) {
		
		
		// Service의 insertDev() 호출 
		int result = service.insertDev(dev);
		

		return "demo/demo";
	}
	
	
	
	// 전체 개발자 조회
	@RequestMapping("/demo/demoList.do")
	public String selectDevList(Model m) {
		
		List<Dev> list = service.selectDevList();
		
		m.addAttribute("list", list);
		
				System.out.println();
				String[] temp = list.get(1).getDevLang();
				for(String a: temp) {
					System.out.println(a);
				}
			
				System.out.println("list : " + list);
		
		return "demo/demoList";
	}
	
	
	
}
