package bubble.test.ex17_적군물방울터트리기;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Player extends JLabel implements Moveable {
	
	private BubbleFrame mContext;	
	private List<Bubble> bubbleList;	// 3.
	
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

	public Player(BubbleFrame mContext) {		
		this.mContext = mContext;			
		initObject();
		initSetting();
		initBackgroundPlayerService();	
	}

	private void initObject() {
		playerR = new ImageIcon("image/playerR.png"); 
		playerL = new ImageIcon("image/playerL.png"); 
		bubbleList = new ArrayList<>();		// 4
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
	
	private void initBackgroundPlayerService() {
//		System.out.println("실행됨");
//		
//		if(mContext.getPlayer()==null) {
//			System.out.println("player==null");
//		}else {
//			System.out.println("palyer!=null");
//		}	// player==null 출력됨 -> 아래 Thread 코드 BackgroundPlayerService(mContext)->this 변경
		
//		if(mContext.getPlayer().getBubbleList()==null) {
//			System.out.println("bubbleList==null");
//		}else {
//			System.out.println("bubbleList!=null");
//		}	// 에러발생
		new Thread(new BackgroundPlayerService(this)).start();	// 2. this->mContext 변경, BackgroundPlayerService 이동
		// => backgroundplayerservice가 new 되는 시점 => mContext = 플레이어객체가 생성되는 시점에서는 버블 생성X
	}

	@Override
	public void attack() {			
		
		new Thread(()->{			
			Bubble bubble = new Bubble(mContext);
			mContext.add(bubble);	
			bubbleList.add(bubble);		// 5
			if(playerWay == PlayerWay.LEFT) {
				bubble.left();
			} else {
				bubble.right();
			}
		}).start();
	
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
