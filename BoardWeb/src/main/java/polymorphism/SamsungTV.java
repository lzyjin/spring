package polymorphism;

public class SamsungTV implements TV{

	private SonySpeaker speaker;
	
	
	public SamsungTV() {
		System.out.println("===> SamsungTV(1) 객체 생성");
	}
	
	public SamsungTV(SonySpeaker speaker) {
		System.out.println("===> SamsungTV(2) 객체 생성");
		this.speaker = speaker;
	}
	
	
	
	public void initMethod() {
		System.out.println("객체 초기화 작업 처리..");
	}
	
	public void destroyMethod() {
		System.out.println("객체 삭제 전에 처리할 로직 처리...");
	}
	
	
	
	
	@Override
	public void powerOn() {
		System.out.println("SamsungTV---전원 켠다.");
	}

	@Override
	public void powerOff() {
		System.out.println("SamsungTV---전원 끈다.");
	}


	public void volumeUp() {
//		speaker = new SonySpeaker();
		speaker.volumeUp();
	}


	public void volumeDown() {
//		speaker = new SonySpeaker();
		speaker.volumeDown();
	}
	
	

}
