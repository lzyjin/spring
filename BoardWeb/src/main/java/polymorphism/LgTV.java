package polymorphism;

public class LgTV implements TV{

//	public void turnOn() {
//		System.out.println("LgTV---전원 켠다.");
//	}
//	public void turnOff() {
//		System.out.println("LgTV---전원 끈다.");
//	}
//	public void soundUp() {
//		System.out.println("LgTV---소리 올린다.");
//	}
//	public void soundDown() {
//		System.out.println("LgTV---소리 내린다.");
//	}
	
	
	@Override
	public void powerOn() {
		System.out.println("LgTV---전원 켠다.");
	}

	@Override
	public void powerOff() {
		System.out.println("LgTV---전원 끈다.");
	}

	@Override
	public void volumeUp() {
		System.out.println("LgTV---소리 올린다.");
	}

	@Override
	public void volumeDown() {
		System.out.println("LgTV---소리 내린다.");
	}
	
	public LgTV() {
		System.out.println("===> LgTV 객체 생성");
	}
	
	public void initMethod() {
		System.out.println("객체 초기화 작업 처리...");
	}
	
	public void destroyMethod() {
		System.out.println("객체 삭제 전에 처리할 로직 처리...");
	}
	
}
