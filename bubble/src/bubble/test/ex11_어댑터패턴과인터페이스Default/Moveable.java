package bubble.test.ex11_어댑터패턴과인터페이스Default;


public interface Moveable {
	public abstract void left();
	public abstract void right();
	public abstract void up();
//	public abstract void down(); 	// 7-3 어댑터패턴(예전문법)을 먼저 사용해보자 -> 7-4 new class(MoveAdapter) 생성
	default public void down() {};	// 14 default 를 넣어주고 abstract 를 지워주고 구현체를 만듦 <- 1.8 버전부터 나온 최신문법
									// => default를 사용하면 인터페이스도 몸체가 있는 메서드를 만들 수 있음(다중상속이 안되는 것이 많기 때문에)
									// => 어댑터 패턴보다는 default를 사용하는 것이 좋음
									// 15 Bubble 로 이동!
}
