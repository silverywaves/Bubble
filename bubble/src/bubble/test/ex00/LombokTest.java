package bubble.test.ex00;


class Cat{
	private String name;	// 변수의 상태는 행위를 통해서 변경시킴
	// 자바에서의 모든 변수는 private 로 걸어야 함.

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
	
public class LombokTest {

	public static void main(String[] args) {
		Cat c = new Cat();
//		System.out.println(c.name);	// error : name 이 private 이기 때문에
		c.setName("체다");
		System.out.println(c.getName());

	}

}
