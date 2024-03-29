package bubble.test.ex16_적군만들기;

import javax.swing.ImageIcon;
import javax.swing.JLabel;	// 1-2 JLabel 임포트

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bubble extends JLabel implements Moveable {	
	
	// 의존성 컴포지션
	private BubbleFrame mContext;	
	private Player player;	
	private BackgroundBubbleService backgroundBubbleService;
	private Enemy enemy;	// 적 물방울 충돌 확인 1
	
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
	public Bubble(BubbleFrame mContext) {	
		this.mContext = mContext;			
		this.player = mContext.getPlayer();	
		this.enemy = mContext.getEnemy();	// 적 물방울 충돌 확인 2
		initObject();
		initSetting();
	}

	private void initObject() {		
		bubble = new ImageIcon("image/bubble.png");		
		bubbled = new ImageIcon("image/bubbled.png");	
		bomb = new ImageIcon("image/bomb.png");		
		
		backgroundBubbleService = new BackgroundBubbleService(this);	
	}
	private void initSetting() {	
		left = false;	
		right = false;	
		up = false;		
		
		x = player.getX();	
		y = player.getY();	
		
		setIcon(bubble);	
		setSize(50, 50);	
		
		state = 0;			// 물방울 상태이므로 0
	}


	@Override
	public void left() {
		left = true;
		for(int i=0;i<400;i++) {	
			x--;					
			setLocation(x,y);	
			
			// 버블이 움직일때마다 왼쪽벽에 부딪힘 여부 체크하기
			if(backgroundBubbleService.leftWall()) {	// true 가 되면 부딪혔다는 뜻
				left = false;							// 상태 변경(left가 끝날때 false) <- 상태는 행위에 의해 변경됨
				break;									// for 문 빠져나가기(그때 멈춰라)
			}
			// 적 물방울 충돌 확인 3 => x좌표 40과 60의 범위 절대값
			// 적 물방울 충돌 확인 4 => y좌표 맞는 범위 : 물의 y좌표=0//(0,0), 적군의 y좌표=50//(50,50)
			// 물의 y좌표(0)-적군의 y좌표(50)=-50 (0) // 0-55=-55 (x) // 0-45=-45 (o) // 0-0=0 (o)
			if((Math.abs(x - enemy.getX()) > 40 && Math.abs(x - enemy.getX()) < 60) 
				&& (Math.abs(y - enemy.getY()) > 0 && Math.abs(y - enemy.getY()) < 50)) {
				System.out.println("물방울이 적군과 충돌");
				attack();		// 적 물방울 충돌 확인 6 => attack 메서드 실행
			}
			
			try {
				Thread.sleep(1);	
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		up();
	}

	@Override
	public void right() {
		right = true;
		for(int i=0;i<400;i++) {	
			x++;								
			setLocation(x,y);	
			
			// 버블이 움직일때마다 오른쪽벽에 부딪힘 여부 체크하기 
			if(backgroundBubbleService.rightWall()) {	// true 가 되면 부딪혔다는 뜻
				right = false;							// 상태 변경(right가 끝날때 false) <- 상태는 행위에 의해 변경됨
				break;
			}
			
			try {
				Thread.sleep(1);	
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		up();
	}
	

	@Override
	public void up() {
		while(true) {
			y--;					
			setLocation(x,y);	
			
			// 버블이 움직일때마다 위쪽벽에 부딪힘 여부 체크하기
			if(backgroundBubbleService.topWall()) {	
				up = false;							// 상태 변경(up 끝날때 false) <- 상태는 행위에 의해 변경됨
				break;	
			}
			
			try {
				Thread.sleep(1);	
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} // end of while
		
		clearBubble();		
	}

	// 적 물방울 충돌 확인 5 => attack 오버라이드 -> 적 물방울 충돌 확인 6 => 충돌시 실행시키
	@Override
	public void attack() {
		state = 1;			// 물방울이 attack 한다는 것은 적군을 가두는 것 -> 상태가 바뀌어야함
		setIcon(bubbled);	// 물방울 아이콘 변경
		
	}
	
	private void clearBubble() {
		try {
			Thread.sleep(3000);	
			setIcon(bomb);		
			Thread.sleep(500);	
			mContext.remove(this);	
			mContext.repaint(); 
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
