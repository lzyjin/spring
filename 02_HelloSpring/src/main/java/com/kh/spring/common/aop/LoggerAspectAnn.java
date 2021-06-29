package com.kh.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class LoggerAspectAnn {
	
	// 1. pointcut 등록
	@Pointcut("execution(* com.kh.spring.memo..*(..))")
	public void memoMethod() {}
	
	
	
	
	// 210629
	// pointcut 하나 더 등록
	@Pointcut("execution(* com.kh.spring..*(..))")
	public void all() {}
	
	
	
	
	
	
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
	

	
	
	// 210629
	@Around("all()")
	public Object aroundTest(ProceedingJoinPoint join) throws Throwable {
		
		// join.proceed() : 이 메소드의 실행을 기준으로 전 / 후 
		// 실행전이면 before, 실행후면 after
		
		log.debug("========= around 메소드 실행전 ==========");
		Signature sig = join.getSignature();
		log.debug(sig.getName() + ":" + sig.getDeclaringTypeName());
		
		Object[] param = join.getArgs();
		
		for(Object p : param) {
			log.debug("파라미터 : " + p.toString());
			// log.debug("{}", p);
		}
		
		log.debug("-----------------------------------------");
		
		
		
//		Object obj = join.proceed(); // 기준 
//		
//		
//		
//		log.debug("========= around 메소드 실행후 ==========");
//		sig = join.getSignature();
//		log.debug(sig.getName() + ":" + sig.getDeclaringTypeName());
//		log.debug("-----------------------------------------");
//		
//		return obj;
		
		// 전처리만 하고싶으면 
		return join.proceed();
	}
	
	
	@Around("execution(* com.kh.spring..*Dao.*(..))")
	public Object checkRuntime(ProceedingJoinPoint join) throws Throwable{
		log.debug("========== dao 성능측정 ==========");
		
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		
		Object obj = join.proceed();
		
		log.debug("==== 측정결과 ====");
		stopwatch.stop();
		
		Signature sig = join.getSignature();
		log.debug(sig.getName() + "/ 소요시간 : " + stopwatch.getTotalTimeMillis() + "ms");
		
		log.debug("-----------------------------------------");
		return obj;
	}
	
	

}
