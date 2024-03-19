package bubble.test.ex13_물방울충돌감지;


public interface Moveable {
	public abstract void left();
	public abstract void right();
	public abstract void up();
	default public void down() {};	
}
