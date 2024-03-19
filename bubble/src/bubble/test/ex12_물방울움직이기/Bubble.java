package bubble.test.ex12_물방울움직이기;

import javax.swing.ImageIcon;
import javax.swing.JLabel;	// 1-2 JLabel 임포트

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bubble extends JLabel implements Moveable {	
	
	// 의존성 컴포지션
	private Player player;	
	
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
	public Bubble(Player player) {	
		this.player = player;	
		initObject();
		initSetting();
		initThread();		// 2. initThread 추가
	}

	private void initObject() {		
		bubble = new ImageIcon("image/bubble.png");		
		bubbled = new ImageIcon("image/bubbled.png");	
		bomb = new ImageIcon("image/bomb.png");		
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

	// 1. 버블은 스레드가 하나만 필요함
	private void initThread() {	// 3 initThread 메서드 추가
		new Thread(()->{		// 4 스레드 start(작동) -람다식
			if(player.getPlayerWay()==PlayerWay.LEFT) {	// 5 조건 걸어주기 : 플레이어가 왼쪽방향인 경우 
				left();		// 6 left() 실행
			} else {		// 7 플레이어가 오른쪽방향인 경우
				right();	// 8 right() 실행
			}
		}).start();
	}
	

	@Override
	public void left() {
		// 12-1 상태 만들어주기
		left = true;
		// 9 물방울이 이동하는 범위를 줘야 함 => while 말고 for 문 사용
		for(int i=0;i<400;i++) {	// 9-2 범위를 400 정도로 정함
			x--;					// 9-4 왼족이동 => x 좌표 - 
			setLocation(x,y);		// 9-3 이동
			try {
				Thread.sleep(1);	// 9-4 움직이는 속도 조정
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// 9-5 범위(400)만큼 이동했다가 위로 이동(up)
		}
		up();
	}

	@Override
	public void right() {
		// 12-1 상태 만들어주기
		right = true;
		for(int i=0;i<400;i++) {	
			x++;					// 10 left 내용 복붙 후 좌표만 수정하면 됨			
			setLocation(x,y);	
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
			y--;			// 11 left 내용 복봍 후 좌표만 수정하면 됨 (up은 범위 없이 계속 위로 올라가게만 설정해주면 됨)		
			setLocation(x,y);	
			try {
				Thread.sleep(1);	
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 12 물방울을 계속 밣사하다보면 속도가 느려짐 -why?-> 스레드가 엄청나게 많이 생성되는데, 사라지지않아서(제거되지 않아) 느려지는 것
	// => 스레드를 메모리에서 제거해주면서 상하좌우 벽에 물방울이 부딪히면 터지도록 수정해보자

}
