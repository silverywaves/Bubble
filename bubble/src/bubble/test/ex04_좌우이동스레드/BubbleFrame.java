package bubble.test.ex04_좌우이동스레드;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BubbleFrame extends JFrame{
	
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
		
			// 키보드 클릭 이벤트
			@Override
			public void keyPressed(KeyEvent e) {	
//				System.out.println(e.getKeyCode());			// 키 코드 이제 필요없으니 지워주자
				switch(e.getKeyCode()) {	
					case KeyEvent.VK_LEFT:	
						if(!player.isLeft()) {
							player.left();
						}
						break;				
					case KeyEvent.VK_RIGHT:	
						if(!player.isRight()) {		// 2-10 if 조건문 생성->오류:right private 상태->player에서 getter setter 생성해주기
							player.right();
						}
						break;				
					case KeyEvent.VK_UP:	
						player.up();
						break;
				}
			}
			
			// 2-11 키보드 해제 이벤트 핸들러
			public void keyReleased(KeyEvent e) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					player.setLeft(false);		// 2-12 키보드를 뗐을 때 움직이지 말라고 false 걸어줌
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
