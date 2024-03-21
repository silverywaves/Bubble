package bubble.test.ex14_물방울메모리제거;

import javax.swing.ImageIcon;
import javax.swing.JLabel;	// 1-2 JLabel 임포트

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bubble extends JLabel implements Moveable {	
	// 1 버블마다 스레드가 생겼으니, 2~3초 후에 메모리에서 스레드를 소멸시키는 작업을 해보자(성능개선) => 물방울 터트리기!
	
	// 의존성 컴포지션
	private BubbleFrame mContext;	// 5-5 BubbleFrame 정보
	private Player player;	
	private BackgroundBubbleService backgroundBubbleService;
	
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
	public Bubble(BubbleFrame mContext) {	// 5-6 
		this.mContext = mContext;			// 5-7
		this.player = mContext.getPlayer();	// 5-8 Error : BubbleFrame 에 @Getter @Setter 추가하기
		initObject();
		initSetting();
		initThread();
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
			
			// 버블이 움직일때마다 왼쪽벽에 부딪힘 여부 체크하기
			if(backgroundBubbleService.leftWall()) {	// true 가 되면 부딪혔다는 뜻
				left = false;							// 3-1 상태 변경(left가 끝날때 false) <- 상태는 행위에 의해 변경됨
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
			
			// 버블이 움직일때마다 오른쪽벽에 부딪힘 여부 체크하기  -> 9 버블서비스로 이동
			if(backgroundBubbleService.rightWall()) {	// true 가 되면 부딪혔다는 뜻
				right = false;							// 3-2 상태 변경(right가 끝날때 false) <- 상태는 행위에 의해 변경됨
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
				up = false;							// 3-3 상태 변경(up 끝날때 false) <- 상태는 행위에 의해 변경됨
				break;	// 2 up 이 끝나는 시점에 물방울을 터트려야 함 => 현재 topWall()이 걸리면 return true 만 하고, up 의 상태를 바꾸진 않음 -> 위치잡아주자							
				// break : 내가 지금 있는 반복을 빠져나갈 때 씀. 상태가  false 면 break 가 없어도 반복문을 빠져나가지만, break 쓰면 성능이 조금 더 좋아짐
			}
			
			try {
				Thread.sleep(1);	
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} // end of while
		// 4-1 up이 끝나는 구간 : while 이 끝나는 곳
		clearBubble();		// 4-2
	}

	// 메서드 = 행위 -> clear(동사) -> bubble(목적어)
	private void clearBubble() {// 4-3 이 메서드를 호출할 애가 up 메서드가 끝났을때밖에 없다면 다른 곳에서 호출할 일이 없으니 private! (다른곳에서도 호출한다면 public)
		// 풍선이 터지는 타이밍은 up 이 끝나고 나서 천장에 버블이 닿고나서 3초 후 (메모리에서 소멸)
		try {
			Thread.sleep(3000);	// 5-1 3초 있다가
			setIcon(bomb);		// 5-2 bubble->bomb 이미지 변경 
			Thread.sleep(500);	// 5-3 0.5초 있다가	-> 5-4 이미지 제거 <- 어디서? : BubbleFrame 에서 사라져야함! => BubbleFrame 정보 필요
			// 자바로 만드는 모든 프로그램들은 main을 가진 클래스(Context=>모든 정보)가 모든 객체(heap)의 정보를 가지고 있음
			mContext.remove(this);	// 5-13 BubbleFrame의 bubble이 메모리에서 소멸
			// 여기까지 했다면 버블을 발사했을 때, 버블을 메모리에서 사라지지만, 화면에서 bomb 없어지지 않는 상태가 됨 -> 메모리에 있는 것들만 잡아서 화면을 다시 그려야 함
			mContext.repaint(); 	// 6 BubbleFrame의 전체를 다시 그리는 작업(메모리에 없는 건 그리지 않음)
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
