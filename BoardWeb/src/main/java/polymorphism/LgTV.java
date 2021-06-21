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
	
}
