package bubble.test.ex08_바닥충돌정지;

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
	
	private boolean leftWallCrash;
	private boolean rightWallCrash;
	
	private final int SPEED = 4;	
	private final int JUMPSPEED = 2;
	
	private ImageIcon playerR, playerL; 

	public Player() {
		initObject();
		initSetting();
		intBackgroundPlayerService();	
	}

	private void initObject() {
		playerR = new ImageIcon("image/playerR.png"); 
		playerL = new ImageIcon("image/playerL.png"); 
	}

	private void initSetting() {
		x = 80; 		
		y = 535; 
		
		left = false;
		right = false;
		up = false;
		down = false;

		leftWallCrash = false;
		rightWallCrash = false;
		

		setIcon(playerR);
		setSize(50, 50);
		setLocation(x, y); 

	}
	
	private void intBackgroundPlayerService() {		
		new Thread(new BackgroundPlayerService(this)).start();
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

	@Override
	public void up() { 
//		System.out.println("up");
		up = true;		
		new Thread(()->{
			for(int i=0; i<130/JUMPSPEED; i++) {	
				y -= JUMPSPEED;		
				setLocation(x,y);	
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			up = false;		
			down();			
		}).start();
	}

	@Override
	public void down() {
//		System.out.println("down");
		down = true;
		new Thread(()->{
//			for(int i=0; i<130/JUMPSPEED; i++) {	// 5 for 문이라 정상작동 x : 무조건 130 만큼 떨어짐 => while 로 변경하자
			while(down) {							
				// 변경완료. 떨어지다가 false 로 변경되면서 캐릭터 멈춤. 그런데 캐릭터 오른쪽 끄트머리가 파란색에 닿게 점프시 착지X(좌표오류) 버그 발생
				// 현재 x 좌표가 캐릭터의 왼쪽하단 꼭지점이기 때문임. x좌표를 왼쪽하단, 오른쪽하단 모두 -1 일때로 수정필요
				// -> 6 BackgroundRlayerService 이동
				y += JUMPSPEED;						
				setLocation(x,y);
				try {
					Thread.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			down = false;
		}).start();
	}
}
