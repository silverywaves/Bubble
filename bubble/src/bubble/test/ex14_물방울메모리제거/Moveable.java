package bubble.test.ex14_물방울메모리제거;


public interface Moveable {
	public abstract void left();
	public abstract void right();
	public abstract void up();
	default public void down() {};	
}
