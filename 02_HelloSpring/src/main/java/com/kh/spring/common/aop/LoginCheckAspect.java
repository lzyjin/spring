package com.kh.spring.common.aop;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.kh.spring.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoginCheckAspect {
	
	@Before("execution(* com.kh.spring.memo..*(..))")
	public void aroundTest(JoinPoint jp) throws Throwable {
		
		log.debug("============ before : 로그인체크 =============");
		Signature sig = jp.getSignature();
		log.debug(sig.getName() + ":" + sig.getDeclaringTypeName());

		// RequestContextHolder 객체를 제공 
		// spring framework의 web모듈 안에서만 사용가능 
		HttpSession session = (HttpSession)RequestContextHolder.currentRequestAttributes().resolveReference(RequestAttributes.REFERENCE_SESSION);
		
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		if(loginMember == null) {
			// aop에서 진행되는 로직을 중단 
			// Exception으로 예외를 발생시킨다 
			
			try {
				throw new Exception("접속권한이 없습니다. 로그인 후 이용하세요 ");
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		
		
		
		log.debug("----------------------------------------------");
		
	}
	
	
	
	
	
	
	
}
