package polymorphism;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {
	
	public static void main(String[] args) {
		
//		SamsungTV tv = new SamsungTV();
//		tv.powerOn();
//		tv.volumeUp();
//		tv.volumeDown();
//		tv.powerOff();
		
//		LgTV tv = new LgTV();
//		tv.turnOn();
//		tv.soundUp();
//		tv.soundDown();
//		tv.turnOff();
		
		
		
		// 다형성 이용하기
//		TV tv = new SamsungTV();
//		TV tv = new LgTV();
//		tv.powerOn();
//		tv.volumeUp();
//		tv.volumeDown();
//		tv.powerOff();
		
		
		
		// 디자인패턴 이용하기 
//		BeanFactory factory = new BeanFactory();
//		
//		TV tv = (TV)factory.getBean(args[0]);
//		
//		tv.powerOn();
//		tv.volumeUp();
//		tv.volumeDown();
//		tv.powerOff();
		
		
		
		// 스프링 IoC 시작하기
		// 1. Spring 컨테이너를 구동한다
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		
		// 2. Spring 컨테이너로부터 필요한 객체를 요청(Lookup)한다 
		TV tv = (TV)factory.getBean("tv");
		
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
		// 3. Spring 컨테이너를 종료한다
		factory.close();
		
		// 출력 결과
//		===> SamsungTV 객체 생성
//		SamsungTV---전원 켠다.
//		SamsungTV---소리 올린다.
//		SamsungTV---소리 내린다.
//		SamsungTV---전원 끈다.
		
		// applicationContext.xml에 등록된 bean을 SamsungTV에서 LgTV로 바꾸면 
		// 출력 결과
//		===> LgTV 객체 생성
//		LgTV---전원 켠다.
//		LgTV---소리 올린다.
//		LgTV---소리 내린다.
//		LgTV---전원 끈다.
		


	}

}
