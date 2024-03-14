package bubble.test.ex03_캐릭터이동;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

// 캐릭터(플레이어)를 만들기

public class Player extends JLabel implements Moveable { //  2-2 인터페이스 구현
	// Moveable 은 추상메서드를 가지고 있음 -> 구현체 Player 는 인터페이스가 들고 있는 최상 메서드를 구현해야 할까?
	// => class Player = new 가능 -> 게임에 존재할 수 있음(추상메서드를 가질 수 없음) => 추상메서드(Moveable)을
	// implements 하면, Player 는 무조건 구현해야하는 의무를 가지게 됨
	// => Player 에 오류표시(빨간줄) 떠있을텐데, Add Unimplemented Methods 해줌 -> 2-3 left, right, up, down 오버라이드
	
	// 3-2 캐릭터의 위치 상태
	private int x; 
	private int y;

	// 3-3 캐릭터의 움직임 상태
	private boolean left;	// left 상태 -> 최초 : 움직이지 않는 상태 -> 기본 셋팅값을 false 로 줘야 함
	private boolean right;	// right 상태 -> 최초 : 움직이지 않는 상태 -> 기본 셋팅값을 false 로 줘야 함
	private boolean up; 	// up 상태 -> 최초 : 움직이지 않는 상태 -> 기본 셋팅값을 false 로 줘야 함
	private boolean down; 	// down 상태 -> 최초 : 움직이지 않는 상태 -> 기본 셋팅값을 false 로 줘야 함
	
	private ImageIcon playerR, playerL; 

	public Player() {
		initObject();
		initSetting();
	}

	private void initObject() {
		playerR = new ImageIcon("image/playerR.png"); 
		playerL = new ImageIcon("image/playerL.png"); 
	}

	private void initSetting() {
		x = 55; 
		y = 535; 
		
		// 3-4 캐릭터의 움직임 상태 기본값 false 설정
		// 캐릭터가 움직인다 = 좌표가 이동한다
		left = false;
		right = false;
		up = false;
		down = false;
		

		setIcon(playerR);
		setSize(50, 50);
		setLocation(x, y); 

	}

	// 2-3 left, right, up, down 오버라이드  -> 3-1 캐릭터의 상태 필요
	@Override
	public void left() {
		// 4-1 키보드 왼쪽 화살표 버튼을 누를 때 이동 -> 이벤트 처리(BubbleFrame.java 이동)
		setIcon(playerL);	// 5-6 왼쪽으로 잘 움직여지나, 캐릭터가 오른쪽방향을 바라보는 상태로 왼쪽으로 움직여 부자연스러움. 이미지 바꿔주기!
		x -= 10;	// 5-4 x 좌표 이동 거리 설정
		setLocation(x,y);	// 5-5 변경된 좌표값 적용하여 위치 출력
	}

	@Override
	public void right() {
		// 4-1 키보드 오른쪽 화살표 버튼을 누를 때 이동 -> 이벤트 처리(BubbleFrame.java 이동)
		setIcon(playerR);	// 5-7 왼쪽으로 이동한 후 왼쪽이미지가 고정됨 -> 좌우에 따라 다른 이미지가 나타나도록 해당 메서드에도 이미지 바꿔주기!
		x += 10;	// 5-2 x 좌표 이동 거리 설정
		setLocation(x,y);	// 5-3 변경된 좌표값 적용하여 위치 출력
	}

	@Override
	public void up() {
		// 4-1 키보드 위쪽 화살표 버튼을 누를 때 이동 -> 이벤트 처리(BubbleFrame.java 이동)
		
	}

	@Override
	public void down() {
		// 4-1 키보드 아래쪽 화살표 버튼을 누를 때 이동 -> 이벤트 처리(BubbleFrame.java 이동)
		
	}
}
