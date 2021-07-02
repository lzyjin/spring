package com.kh.spring.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerInterceptor extends HandlerInterceptorAdapter{
	
	
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
																						throws Exception {
	
		// 리턴값이 true여야만 다음으로 넘어간다 
		
		log.debug("===== start =====");
		log.debug(request.getRequestURI());
		log.debug("-----------------");
		
		
		
		
		//LoggerInterceptor 클래스를 servlet처럼 이용할 수 있다 
		
		Map map = request.getParameterMap();
		
		request.getSession().getAttribute("loginMember");
		
		
		
		return super.preHandle(request, response, handler); // 이렇게 super의 preHandle()을 호출하면 무조건 true 리턴 
	}

	
	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
							Object handler, ModelAndView modelAndView) throws Exception {
		
		// ModelAndView : 프론트에 보낼 데이터가 저장된 객체와 화면(프론트페이지)
		
		log.debug("============== view ==============");
		log.debug("view로 응답하기 전");
		// log.debug("응답페이지 : {}", modelAndView.getViewName());
		log.debug("----------------------------------");
		
		
		// 어떤 화면으로 출력하는지(넘어가는지)를 알 수 있다
		// modelAndView.getViewName(); 
		
		// model에 대한 정보 알 수 있따
		// modelAndView.getModel().get("");

		super.postHandle(request, response, handler, modelAndView);
	}

	
}
