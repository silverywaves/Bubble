package bubble.test.ex11_어댑터패턴과인터페이스Default;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BubbleFrame extends JFrame{
	
	private JLabel backgroundMap;
	private Player player;
	
	// 1-1 물방울의 이동 방향을 지정하려면 플레이어의 방향을 알아야 함
	// 1-2 Player 의 움직임 상태로 방향을 알수있는가? (X) -why?-> 캐릭터 정지시 움직임 상태가 false
	// 1-3 그렇다면 방향을 알려면 어떻게 해야할까? Player.java 로 이동!
	
	public BubbleFrame() { 
		initObject();		
		initSetting();		
		initListener();		
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
	
	private void initListener() {	
		addKeyListener(new KeyAdapter(){	
			// 키보드 클릭 이벤트 핸들러
			@Override
			public void keyPressed(KeyEvent e) {	
				switch(e.getKeyCode()) {	
					case KeyEvent.VK_LEFT:	
						if(!player.isLeft() && !player.isLeftWallCrash()) {	
							player.left();
						}
						break;				
					case KeyEvent.VK_RIGHT:	
						if(!player.isRight() && !player.isRightWallCrash()) {	
							player.right();
						}
						break;				
					case KeyEvent.VK_UP:	
						if(!player.isUp() && !player.isDown()) {  
							player.up();
						}
						break;
					// 13 버블은 스페이스바를 눌렀을 때 생성
					case KeyEvent.VK_SPACE:	
//						Bubble bubble = new Bubble();	// 버블 객체를 만들때 오류 발생 -why?-> new Bubble() 에 플레이어 정보가 없어서
						Bubble bubble = new Bubble(player);	// 13-1
						add(bubble);		// 13-3 JFrame 에 버블 넣어주기
						break;	// 13-2
				}
			}
			
			// 키보드 해제 이벤트 핸들러
			public void keyReleased(KeyEvent e) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					player.setLeft(false);	
					break;
				case KeyEvent.VK_RIGHT:
					player.setRight(false);
					break;
				}
			}
			
		});			
	}
	
	public static void main(String[] args) {
		new BubbleFrame();	
	}
}
