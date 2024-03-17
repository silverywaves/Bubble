package bubble.test.ex04_좌우이동스레드;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Player extends JLabel implements Moveable {
	private int x; 
	private int y;

	private boolean left;	 
	private boolean right; 
	private boolean up;  
	private boolean down; 	 
	
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
		
		left = false;
		right = false;
		up = false;
		down = false;
		

		setIcon(playerR);
		setSize(50, 50);
		setLocation(x, y); 

	}

	// 이벤트 루프(메모리공간)에 이벤트 순차적 저장(큐) -> 이벤트 핸들러(stack 공간-메서드 내부) 호출 -> 이벤트 실행
	// **주의1 : 그림 변경 시점은 이벤트 루프에 모든 임무(TASK)가 완료될 때 REPRINT
	// **주의2 : 메인스레드만 있으면(스레드가 하나면) 이벤트 루프에 KEY 전달시 하나밖에 전송하지 못함! (RIGHT->UP 시 RIGHT 멈춤. 동시진행 x) 
	@Override
	public void left() {
		left = true;		// 2-8 한번 눌리면 true 라서 종료되지 않음 -> 2-9 키가 눌리면 left 메서드 더는 실행되지 않도록 해야함 -> BubbleFrame 이동
		new Thread(()->{		// 2-5 left 에도 동일하게 스레드 추가해줌. 그런데 움직일때마다 스레드의 추가삭제 반복->낭비
			while(left) {		// 2-6 ->while(true) 문으로 한번만 사용하도록 조건 걸어줌 -> 키 누르면 순간이동한듯이 한번에 크게 움직임
				// 2-8 while(left) 로 변경
				setIcon(playerL);	
				x -= 1;	
				setLocation(x,y);
				try {
					Thread.sleep(10);		// 2-7 한번에 움직이는 것 방지 -> sleep 걸어줌 -> 가속도붙음
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}	
		}).start(); 
//		setIcon(playerL);	
//		x -= 1;		// 1 캐릭터 움직임 범위 1로 조정
//		setLocation(x,y);	
	}

	@Override
	public void right() {
		// 2-1 스레드 하나 실행해보자
//		new Thread(new Runnable() {	 // 2-2 원래는 Thread(Runnable-인터페이스 target)->run 추상메서드 오버라이드 형식이나, 
//									 // 2-3 요즘은 람다식으로 더 많이 씀
//			@Override
//			public void run() {
//			}
		right = true;
		new Thread(()->{		// 2-3 람다식 표현 (= Runnable)
			while(right) {
				setIcon(playerR);	// 2-4 캐릭터 움직임 코드를 스레드 안으로 옮겨줌
				x += 1;		
				setLocation(x,y);	
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();  
		
//		setIcon(playerR);		// 2-4 캐릭터 움직임 코드를 스레드 안으로 옮겨줌
//		x += 1;		// 1 캐릭터 움직임 범위 1로 조정
//		setLocation(x,y);	
	}

	@Override
	public void up() {

	}

	@Override
	public void down() {
		
	}
}
