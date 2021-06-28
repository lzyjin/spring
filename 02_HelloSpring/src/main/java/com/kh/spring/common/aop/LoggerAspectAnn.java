package com.kh.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class LoggerAspectAnn {
	
	// 1. pointcut 등록
	@Pointcut("execution(* com.kh.spring.memo..*(..))")
	public void memoMethod() {}
	
	// 2. advisor 등록 
	@Before("memoMethod()")
	public void loggerTest(JoinPoint jp) {
		log.debug("========== 어노테이션으로 적용한 AOP(@Before) ==========");
		log.debug("------------------ 시작 ------------------");
		Signature sig = jp.getSignature();
		log.debug(sig.getName() + ":" + sig.getDeclaringTypeName());
		log.debug("-----------------------------------------");
	}
	
	@After("memoMethod()")
	public void loggerTestAfter(JoinPoint jp) {
		log.debug("========== 어노테이션으로 적용한 AOP(@After) ==========");
		log.debug("------------------ 시작 ------------------");
		Signature sig = jp.getSignature();
		log.debug(sig.getName() + ":" + sig.getDeclaringTypeName());
		log.debug("-----------------------------------------");
	}
	

}
