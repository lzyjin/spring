package com.kh.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

import lombok.extern.slf4j.Slf4j;

// aop에 해당하는 기능을 구현한 객체
// ( 공통의 기능을 따로 뽑아낸 객체 )
@Slf4j
public class LoggerAspect {
	
	// before advisor 구현
	// xml에 등록할거라서 어노테이션 적용안함
	public void loggerBefore(JoinPoint jp) {
		
		Signature sig = jp.getSignature();
		log.debug("========== aop before 적용 ==========");
		log.debug(sig.getName() + " : " + sig.getDeclaringTypeName());
		log.debug("------------------------------------");
	}

}
