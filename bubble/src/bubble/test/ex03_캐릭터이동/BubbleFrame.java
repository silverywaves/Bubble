package bubble.test.ex03_캐릭터이동;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;

public class BubbleFrame extends JFrame{
	
	private JLabel backgroundMap;
	private Player player;
	
	public BubbleFrame() {
		initObject();		
		initSetting();		
		initListener();		// 5 initListener 호출
		setVisible(true);
	}
	
	private void initObject() {	
		backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));	
		setContentPane(backgroundMap);	
		
		player = new Player();
		add(player);		
	}
	
	private void initSetting() {	
		setSize(1000,640);	
		setLayout(null);	
		
		setLocationRelativeTo(null);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initListener() {	// 4-2 initListener 메서드 생성
//		addKeyListener(new KeyListener() {		// addKeyListener : Ctrl+클릭 해서 상세내용 보면, 괄호안에 KeyListener 타입이 들어가야 함
			// -> KeyListener 타입의 상세내용 보면, 인터페이스인 점과 추상메서드 3개(keyTyped, keyReleased, keyPressed)를 가지고 있는 것 확인 가능
			// => 괄호안에 new KeyListener 입력 후 사용하기 원하는 KeyPressed 메서드를 오버라이딩 -> 오류 발생 -why?-> 메서드 일부만 구현 불가/모두 구현 되어야 함			
//			@Override
//			public void keyPressed(KeyEvent e) {
//				// 일부만 구현시 오류 발생	
//			});
		
		// => 4-3 어댑터패턴 사용
		addKeyListener(new KeyAdapter(){	
		// KeyAdapter : 추상클래스 <- KeyListener 를 implements 받아서 추상메서드 3가지(keyTyped, keyReleased, keyPressed)를 이미 구현해놓음 -> 오류 발생 x
								// -> 사용하기 원하는 메서드만 오버라이드로 불러와서 사용 가능
			@Override
			public void keyPressed(KeyEvent e) {		// 4-4 KeyAdapter 를 사용해 keyPressed 메서드만 재정의 진행
				System.out.println(e.getKeyCode());		// 4-5 키보드 버튼 코드를 출력하여 상하좌우 버튼 코드 확인
				// => 결과 : 좌 39 / 우 37 / 상 38 / 하 40
				
				switch(e.getKeyCode()) {		// 4-6 키보드 코드가 입력될 때 이벤트가 발생하는 switch 조건문 작성
//					case 37:				// 만약, 오른쪽 화살표 버튼이 눌렸다면,
//						break;				// 멈춰라
//					case 39:				// 만약, 왼쪽 화살표 버튼이 눌렸다면,
//						break;				// 멈춰라
//					case 38:				// 만약, 위쪽 화살표 버튼이 눌렸다면,
//						break;				// 멈춰라
//					case 40:				// 만약, 아래쪽 화살표 버튼이 눌렸다면,
//						break;				// 멈춰라
					// => 굉장히 가독성이 좋지 않음. 주석 안달아놓으면 1도 모를듯..! 이럴 때 쓰는 방법이 KeyEvent.VK_방향
					case KeyEvent.VK_LEFT:	// Ctrl+클릭 해보면 0x25(16진수)=37 확인 됨
						// 4-7 왼쪽 키가 눌리면 player 의 left 메서드를 호출하여 x 좌표를 변경하도록 함
						player.left();
						break;				
					case KeyEvent.VK_RIGHT:	// Ctrl+클릭 해보면 0x27(16진수)=39 확인 됨
						// 4-8 오른쪽 키가 눌리면 player 의 right 메서드를 호출하여 x 좌표를 변경하도록 함
						player.right();
						break;				
					case KeyEvent.VK_UP:	// Ctrl+클릭 해보면 0x26(16진수)=38 확인 됨
						// 4-9 위쪽 키가 눌리면 player 의 up 메서드를 호출하여 y 좌표를 변경하도록 함
						player.up();
						break;				
//					case KeyEvent.VK_DOWN:	// Ctrl+클릭 해보면 0x28(16진수)=40 확인 됨
//						// 4-10 아래쪽 키가 눌리면 player 의 down 메서드를 호출하여 y 좌표를 변경하도록 함
//						player.down();
//						break;				
//						 => 그런데 보글보글 게임에서는 캐릭터가 좌우 혹은 상으로 움직이면서 아래로 떨어질 순 있어도, 아래쪽 키로 떨어지진 않음 -> 해당 이벤트는 불필요한 내용으로 삭제!
//							다만, down() 메서드는 필요함! up 으로 점프 후에 down 으로 떨어져야 하기 때문!
					// 5-1 여기까지 완료 되었으면, 메서드를 완성시키러 가자! Player.java 로 이동!
				}
			}
			
		});			
	}
	
	public static void main(String[] args) {
		new BubbleFrame();	
	}
}
