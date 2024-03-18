package bubble.test.ex10_물방울만들기;

import javax.swing.ImageIcon;
import javax.swing.JLabel;	// 1-2 JLabel 임포트

import lombok.Getter;
import lombok.Setter;

// 3 Getter, Setter
@Getter
@Setter
public class Bubble extends JLabel{		// 1-1 버블도 그림이니까 JLabel 이 되어야 함
	// 2 Player 의 기본내용 복붙
	
	// 11-2 의존성 컴포지션
	private Player player;		// 11-3 player 의 정보 필요 <= 생성자로 받아옴
	
	// 10 버블이 만들어질 때 최초 위치 상태 => 플레이어의 위치와 동일해야 함 (버블은 플레이어에 의존적) => 11-1 의존성 컴포지션 필요
	private int x; 
	private int y;

	// 움직임 상태
	private boolean left;	 
	private boolean right; 
	private boolean up;  
//	private boolean down; 	 // 3 물방울은 down 상태 없으니 삭제
	
//	// 벽에 충돌한 상태		// 4 벽에 충돌하면 끝임(멈춘다) 필요없으니 삭제
//	private boolean leftWallCrash;
//	private boolean rightWallCrash;
	
	// 5-1 물방울이 적군을 맞춘 상태 설정
	private int state;			// 5-2 0(일반 물방울), 1(적을 가둔 물방울) 로 진행예정
	private ImageIcon bubble;	// 5-3 일반 물방울 
	private ImageIcon bubbled;	// 5-4 적을 가둔 물방울 
	private ImageIcon bomb;		// 5-3 물방울이 터진 상태
	
	
//	// 속도 상태		// 6 물방울의 속도는 항상 같기때문에 필요 없음. 삭제
//	private final int SPEED = 4;	
//	private final int JUMPSPEED = 2; 	// up, down
	
	// 7 생성자
	public Bubble(Player player) {	// 7-1	// 11-4 매개변수(타입 변수명) 등록 => (Player player) 
		this.player = player;		// 11-5 매개변수와 필드값을 구분하기 위해 객체 자신을 참조(this.필드 = 매개변수)
		initObject();
		initSetting();
	}
	
	private void initObject() {		// 7-2
		// 8 물방울들을 메모리에 띄어주기
		bubble = new ImageIcon("image/bubble.png");		// 8-1
		bubbled = new ImageIcon("image/bubbled.png");	// 8-2
		bomb = new ImageIcon("image/bomb.png");			// 8-3
	}
	private void initSetting() {	// 7-3
		// 9 기본 상태 셋팅
		left = false;	// 9-1
		right = false;	// 9-2
		up = false;		// 9-3
		
		x = player.getX();	// 11-6 x 좌표(player의 x좌표와 같게 함)
		y = player.getY();	// 11-7 y 좌표(player의 y좌표와 같게 함) 
		
		setIcon(bubble);	// 11-8 기본상태의 물방울은 일반 물방울 모양
		setSize(50, 50);	// 11-9 물방울 사이즈 지정
		
		state = 0;			// 11-10 물방울 상태이므로 0 -> 12 BubbleFrame 이동
	}
	
	
}
