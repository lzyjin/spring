package polymorphism;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {
	
	public static void main(String[] args) {
		
		// scope 속성 
		
		// 1. 스프링 컨테이너 구동 
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		
		
		// 2. 스프링 컨테이너로부터 필요한 객체를 요청 
		TV tv1 = (TV)factory.getBean("tv");
		
//		TV tv2 = (TV)factory.getBean("tv");
//		TV tv3 = (TV)factory.getBean("tv");
		
		tv1.powerOn();
		tv1.volumeUp();
		tv1.volumeDown();
		tv1.powerOff();
		
		
		// 3. 스프링 컨테이너 종료
		factory.close();
		

	}

}
