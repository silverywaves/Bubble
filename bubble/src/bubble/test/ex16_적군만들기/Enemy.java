package bubble.test.ex16_적군만들기;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Enemy extends JLabel implements Moveable { // 1 적군도 그림이 되어야 함 -> JLabel, 행동 제약 있음 -> Moveable
	// 2 Player 겹치는 내용 가져오기
	
	private BubbleFrame mContext;		
	
	// 위치상태
	private int x; 
	private int y;
	
	// 3 적군의 방향 -> EnemyWay enum 생성
	private EnemyWay enemyWay;

	// 움직임 상태
	private boolean left;	 
	private boolean right; 
	private boolean up;  
	private boolean down; 	 
	
	// 적 물방울 충돌 확인 11 => enemy 상태 부여
	private int state;	// 0(살아있는 상태), 1(물방울에 갇힌 상태)
	
	// 4 적군은 키보드로 움직이는 것이 아니라 자동으로 움직임 -> 벽에 충돌한 상태 필요 x
	// 5 적군은 BubbleFrame 시작할 때 등장 -> BubbleFrame 이동
	
	// 적군 속도 상태
	private final int SPEED = 3;	
	private final int JUMPSPEED = 1;	

	
	private ImageIcon enemyR, enemyL; 

	public Enemy(BubbleFrame mContext) {
		this.mContext = mContext;			
		initObject();
		initSetting();
//		initBackgroundEnemyService();	
	}
	// 적군 이미지
	private void initObject() {
		enemyR = new ImageIcon("image/enemyR.png"); 
		enemyL = new ImageIcon("image/enemyL.png"); 
	}
	// 적군 처음 위치(제일 꼭대기층)
	private void initSetting() {
		x = 480; 		
		y =178; 
		
		left = false;
		right = false;
		up = false;
		down = false;
		
		
		// 적 물방울 충돌 확인 12 => enemy 살아있는 상태로 초기 설정
		state = 0;

		
		enemyWay = EnemyWay.RIGHT;	
		
		setIcon(enemyR);
		setSize(50, 50);
		setLocation(x, y); 

	}
	
	private void intBackgroundEnemyService() {		
//		new Thread(new BackgroundEnemyService(this)).start();
	}


	@Override
	public void left() {
		enemyWay = EnemyWay.LEFT;		
		left = true;		
		new Thread(()->{		
			while(left) {		
				setIcon(enemyL);	
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
		enemyWay = EnemyWay.RIGHT;	
		right = true;
		new Thread(()->{		
			while(right) {
				setIcon(enemyR);
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
