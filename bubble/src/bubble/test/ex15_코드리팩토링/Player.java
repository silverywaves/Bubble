package bubble.test.ex15_코드리팩토링;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Player extends JLabel implements Moveable {
	
	private BubbleFrame mContext;		// 6-1 mContext 받음
	
	// 위치상태
	private int x; 
	private int y;
	
	// 플레이어의 방향 
	private PlayerWay playerWay;

	// 움직임 상태
	private boolean left;	 
	private boolean right; 
	private boolean up;  
	private boolean down; 	 
	
	// 벽에 충돌한 상태
	private boolean leftWallCrash;
	private boolean rightWallCrash;
	
	// 플레이어 속도 상태
	private final int SPEED = 4;	
	private final int JUMPSPEED = 2;	// up, down
	
	private ImageIcon playerR, playerL; 

	public Player(BubbleFrame mContext) {		// 6-2
		this.mContext = mContext;				// 6-3
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
		
		playerWay = PlayerWay.RIGHT;	
		setIcon(playerR);
		setSize(50, 50);
		setLocation(x, y); 

	}
	
	private void intBackgroundPlayerService() {		
		new Thread(new BackgroundPlayerService(this)).start();
	}

	@Override
	public void attack() {			// 4 attack 오버라이드
		
		new Thread(()->{			// 12 Thread 생성하기
			// 13 생성시켜둔 버블 객체와 조건문 가져오기 => 실행시키면 정상 작동될까? (X) => Bubble을 화면에 add 하지 않았음
			Bubble bubble = new Bubble(mContext);
			mContext.add(bubble);	// 14 Bubble 화면에 add! => 실행시키면 정상작동
			if(playerWay == PlayerWay.LEFT) {
				bubble.left();
			} else {
				bubble.right();
			}
		}).start();
		
//		Bubble bubble = new Bubble(mContext); 	// 5 버블 객체 생성 -> mContext(=BubbleFrame) error -> 받아와야함
//		// 7-1 캐릭터 방향에 따라 left or right 메서드 실행시키기
//		if(playerWay == PlayerWay.LEFT) {	// 7-2 만약 플레이어 방향이 왼쪽이면
//			bubble.left();					// 7-3 left 메서드 실행   -> bubbleFrame 이동
//			// 10 left()가 바로 실행되면서 스레드가 만들어지지 않았기 때문 => Bubble 이동
//		} else {
//			bubble.right();
//		}
	}

	@Override
	public void left() {
		playerWay = PlayerWay.LEFT;		
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
		playerWay = PlayerWay.RIGHT;	
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
			while(down) {						
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
