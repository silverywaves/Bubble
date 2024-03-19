package bubble.test.ex13_물방울충돌감지;

import javax.swing.ImageIcon;
import javax.swing.JLabel;	// 1-2 JLabel 임포트

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bubble extends JLabel implements Moveable {	
	
	// 의존성 컴포지션
	private Player player;	
	private BackgroundBubbleService backgroundBubbleService;	// 5 의존
	// 참고 : 플레이어는 플레이어백그라운드서비스에 의존하지 않고 자체적으로 스레드가 돌면서 처리됐었음
	
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
	public Bubble(Player player) {	// 5 이때 의존해야함
		this.player = player;	
		initObject();
		initSetting();
		initThread();
	}

	private void initObject() {		
		bubble = new ImageIcon("image/bubble.png");		
		bubbled = new ImageIcon("image/bubbled.png");	
		bomb = new ImageIcon("image/bomb.png");		
		
		backgroundBubbleService = new BackgroundBubbleService(this);	// 6 버블이 만들어질때마다 new!
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

	private void initThread() {	
		new Thread(()->{		
			if(player.getPlayerWay()==PlayerWay.LEFT) {
				left();		
			} else {		
				right();	
			}
		}).start();
	}
	

	@Override
	public void left() {
		left = true;
		for(int i=0;i<400;i++) {	
			x--;					
			setLocation(x,y);	
			
			// 7 버블이 움직일때마다 왼쪽벽에 부딪힘 여부 체크하기
			if(backgroundBubbleService.leftWall()) {	// true 가 되면 부딪혔다는 뜻
				break;									// for 문 빠져나가기(그때 멈춰라)
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
			
			// 8 버블이 움직일때마다 오른쪽벽에 부딪힘 여부 체크하기  -> 9 버블서비스로 이동
			if(backgroundBubbleService.rightWall()) {	// true 가 되면 부딪혔다는 뜻
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
			
			// 15 버블이 움직일때마다 위쪽벽에 부딪힘 여부 체크하기
			if(backgroundBubbleService.topWall()) {	
				break;								
			}
			
			try {
				Thread.sleep(1);	
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


}
