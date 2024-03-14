package bubble.test.ex03_캐릭터이동;

// 캐릭터를 움직여보자(상, 하, 좌, 우)

public interface Moveable {
	// 1 캐릭터가 움직일 수 있는 방향에 대한 함수 생성(void left, right, up, down) -> 2-1 Player 로 이동
	public abstract void left();
	public abstract void right();
	public abstract void up();
	public abstract void down();
}
