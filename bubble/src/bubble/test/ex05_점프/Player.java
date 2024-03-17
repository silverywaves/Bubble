package bubble.test.ex05_점프;

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
	
	// 1 플레이어 속도 상태
	private final int SPEED = 4;	// 상수는 대문자로
	// 4 jump 스피드 추가하기
	private final int JUMPSPEED = 2;
	
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


	@Override
	public void left() {
		left = true;		
		new Thread(()->{		
			while(left) {		
				setIcon(playerL);	
				x -= SPEED;	
				setLocation(x,y);
				try {
					Thread.sleep(10);		
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}	
		}).start(); 
	}

	@Override
	public void right() {

		right = true;
		new Thread(()->{		
			while(right) {
				setIcon(playerR);
				x += SPEED;		
				setLocation(x,y);	
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();  
		
	}

	// left + up, right + up 동시에 실행시키려면 스레드를 만들어야 함
	@Override
	public void up() {  // 6 up 인 상태에서는 up 이 더 실행되지 않아야 함. 아니면 창밖으로 넘어가버림 -> bubbleFrame 이동
		System.out.println("up");
		up = true;		// 2 up 메서드 수정시작
		new Thread(()->{
			for(int i=0; i<130/JUMPSPEED; i++) {		// 3 끝이 위치함->for문 사용
				y -= JUMPSPEED;		// 점프하여 이동하는 위치/JUMPSPEED 해주는 이유 : 내가 움직이고 싶은 위치는 130까지인데, 현재 JUMPSPEED 가 2라서 130*2 만큼 움직임
				setLocation(x,y);	// 원하는 위치까지만 이동하려면 2(JUMPSPEED 만큼 나눠줘야 함)
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			up = false;		// 4 jump 가 끝났으니 false 적용해줘야 함(좌우는 키보드를 뗄 때 false 가 들어감)
			down();			// 5 down 메서드 실행
		}).start();
	}

	@Override
	public void down() {
		System.out.println("down");
		down = true;
		new Thread(()->{
			for(int i=0; i<130/JUMPSPEED; i++) {	
				y += JUMPSPEED;		//  up 이랑 똑같이 적되, 방향만 바꿔주기 
				setLocation(x,y);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			down = false;
		}).start();
	}
}
