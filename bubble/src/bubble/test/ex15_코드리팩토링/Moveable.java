package bubble.test.ex15_코드리팩토링;


public interface Moveable {
	public abstract void left();
	public abstract void right();
	public abstract void up();
	default public void down() {};	
	// 3 메서드 추가 생성 -> default 인 이유 : bubble 은 attack 할 수 없음. 움직일 뿐. (플레이어가 attack 함) -> player 이동
	default public void attack() {};
}
