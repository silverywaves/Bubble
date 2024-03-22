package bubble.test.ex15_코드리팩토링;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter		// 5-9
@Setter
public class BubbleFrame extends JFrame{
	
	private BubbleFrame mContext = this;	
	private JLabel backgroundMap;
	private Player player;
	
	
	public BubbleFrame() { 
		initObject();		
		initSetting();		
		initListener();		
		setVisible(true);
	}
	
	private void initObject() {		
		backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));

		setContentPane(backgroundMap);	
		
		player = new Player(mContext);		// 9 mContext 넘겨주기 => 이대로 실행시 문제 발생! -> Player 이동
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
					
					// 1 BubbleFrame 의 입장에서는 스페이스바가 눌리면 버블이 생성되는 것이 맞지만, 행위의 주체는 플레이어
					case KeyEvent.VK_SPACE:	
//						Bubble bubble = new Bubble(mContext);	// 2 플레이어가 뉴 버블 생성 => 행위자를 바꿔주자 => moveable 이동
//						add(bubble);		
						player.attack();	// 8 player 의 attack 메서드 호출
						break;	
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
