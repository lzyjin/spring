package com.kh.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerTest {
	
	// 로그를 출력해줄 객체를 선언하기
	private Logger logger = LoggerFactory.getLogger(LoggerTest.class);

	public static void main(String[] args) {
		
		new LoggerTest().loggerTest();
		
	}
	
	public void loggerTest() {
			
		// Logger에서 제공하는 각 메소드를 이용해서 로그를 출력한다
		// 메소드의 매개변수는 String 타입
		// 메소드이름이 log4j의 level
		logger.debug("debug 로그");
		logger.info("info 로그");
		logger.warn("warn 로그");
		logger.error("error 로그");
		
		// FATAL > ERROR > WARN > INFO > DEBUG > TRACE
		
		// log4j.xml에서 com.kh.spring 의 level을 info로 설정했기 때문에 debug로그가 출력되지않는다
		
		// INFO : com.kh.spring.LoggerTest - info 로그
		// WARN : com.kh.spring.LoggerTest - warn 로그
		// ERROR: com.kh.spring.LoggerTest - error 로그
	}

}
