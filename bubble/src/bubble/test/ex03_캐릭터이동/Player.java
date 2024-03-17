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
	// 이벤트 루프(메모리공간)에 이벤트 순차적 저장(큐) -> 이벤트 핸들러(stack 공간-메서드 내부) 호출 -> 이벤트 실행
	// 6-1 **주의1 : 그림 변경 시점은 이벤트 루프에 모든 임무(TASK)가 완료될 때 REPRINT
	// 7-1 **주의2 : 메인스레드만 있으면(스레드가 하나면) 이벤트 루프에 KEY 전달시 하나밖에 전송하지 못함! (RIGHT->UP 시 RIGHT 멈춤. 동시진행 x) 
	@Override
	public void left() {
		// 4-1 키보드 왼쪽 화살표 버튼을 누를 때 이동 -> 이벤트 처리(BubbleFrame.java 이동)
		setIcon(playerL);	// 5-6 왼쪽으로 잘 움직여지나, 캐릭터가 오른쪽방향을 바라보는 상태로 왼쪽으로 움직여 부자연스러움. 이미지 바꿔주기!
		x -= 10;	// 5-4 x 좌표 이동 거리 설정
		setLocation(x,y);	// 5-5 변경된 좌표값 적용하여 위치 출력
	}

	@Override
	public void right() {
//		try {
//			Thread.sleep(2000);		// 6-2 오른쪽 키를 누르면 2초동안 멈췄다가 이동하게 만들어 둔 후 캐릭터를 연속으로 움직여 보자!
//		} catch (InterruptedException e) {		// -> 키보드를 누른만큼 순간이동한 것 처럼 한번에 움직임
//			e.printStackTrace();
//		}		
		// 4-1 키보드 오른쪽 화살표 버튼을 누를 때 이동 -> 이벤트 처리(BubbleFrame.java 이동)
		setIcon(playerR);	// 5-7 왼쪽으로 이동한 후 왼쪽이미지가 고정됨 -> 좌우에 따라 다른 이미지가 나타나도록 해당 메서드에도 이미지 바꿔주기!
		x += 10;	// 5-2 x 좌표 이동 거리 설정
		setLocation(x,y);	// 5-3 변경된 좌표값 적용하여 위치 출력
	}

	@Override
	public void up() {
		// 키보드 위쪽 화살표 버튼을 누를 때 이동

	}

	@Override
	public void down() {
		// 키보드 아래쪽 화살표 버튼을 누를 때 이동 
	
	}
}
