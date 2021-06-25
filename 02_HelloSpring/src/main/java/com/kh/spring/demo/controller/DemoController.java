package com.kh.spring.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // 이 어노테이션이 있으면 bean을 등록했다는 것 
public class DemoController {
	
	@Autowired //DemoService가 bean이 등록되어있지 않으면 에러 
//	private DemoService service;

	
	// controller의 역할
	// 클라이언트가 보낸 매핑주소와 일치하는 메소드를 가지고 있다
	// 메소드를 가짐 = 그 기능을 수행시킨다 
	// controller : 주소와 매핑되는 메소드(서비스)를 구현한 클래스 
	
	// 주소 매핑을 위해 어노테이션을 사용한다 
	// @RequestMapping("/demo/demo.do")
//	@RequestMapping(value="/demo/demo.do", method= {RequestMethod.GET, RequestMethod.POST}) // 안적으면 get방식, post방식 둘다 받음 ..?
	@RequestMapping("/demo/demo.do")
	public String demo() {
		
		System.out.println("/demo/demo.do 실행");
		
//		service.selectAll();
		
		return ""; // return값을 viewResolver에게 보낸다. 공란을 반환하게되면 /WEB-INF/views/.jsp 로 이동하게된다 
		// RequestDispatcher.forward(); 와 같다 
	}
	
	
}
