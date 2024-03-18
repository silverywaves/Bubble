package bubble.test.ex11_어댑터패턴과인터페이스Default;

import javax.swing.ImageIcon;
import javax.swing.JLabel;	// 1-2 JLabel 임포트

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bubble extends JLabel implements Moveable {	// 16 implements Moveable 후 add unimplements method 해줌
	// 11 문제발생 = JLabel 이미 있기때문에 extends MoveAdapter 사용 불가(다중상속 불가)-> 사용하기 힘듦 -> 새로운 문법으로 다시 만들어보자 
	// -> 12-1 MoveAdapter 삭제하러 가자
	
	// 의존성 컴포지션
	private Player player;	
	
	// 버블이 만들어질 때 최초 위치 상태 => 플레이어의 위치와 동일해야 함 (버블은 플레이어에 의존적) => 의존성 컴포지션 필요
	private int x; 
	private int y;

	// 움직임 상태
	private boolean left;	 
	private boolean right; 
	private boolean up;  

	// 물방울이 적군을 맞춘 상태 설정
	private int state;			// 0(일반 물방울), 1(적을 가둔 물방울) 로 진행예정
	private ImageIcon bubble;	// 일반 물방울 
	private ImageIcon bubbled;	// 적을 가둔 물방울 
	private ImageIcon bomb;		// 물방울이 터진 상태
	
	
	// 생성자
	public Bubble(Player player) {	
		this.player = player;	
		initObject();
		initSetting();
	}
	
	private void initObject() {		
		bubble = new ImageIcon("image/bubble.png");		
		bubbled = new ImageIcon("image/bubbled.png");	
		bomb = new ImageIcon("image/bomb.png");		
	}
	private void initSetting() {	
		left = false;	
		right = false;	
		up = false;		
		
		x = player.getX();	
		y = player.getY();	
		
		setIcon(bubble);	// 6 초기셋팅을 할때 버블의 방향이 결정되어야 함 -> 플레이어의 방향에 따라 달라짐
		setSize(50, 50);	
		
		state = 0;			// 물방울 상태이므로 0
	}

	// 17 left, right, up 만 추가됨 (down x)
	@Override
	public void left() {
		
	}

	@Override
	public void right() {
		
	}

	@Override
	public void up() {
		
	}
	
	// 7-1 버블이 움직일 수 있는 Moveable은 위, 오른쪽, 왼쪽밖에 없으나, 인터페이스(Moveable)를 보면 down 有 
	// => down 때문에 Bubble 이 Moveable 을 implements 하게되면 강제성이 부여되어 down 도 만들어줘야 함
	// => 7-2 Moveable.java 로 이동해서 down() 에 default 를 걸어주자
	
}
