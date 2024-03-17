package bubble.test.ex06_충돌감지;

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
		// 8 bubbleFrame 이 실행될 때, 여기서 객체 만듦 -> player 객체를 만들어서 backgroundplayer 실행시킬것 -> player 로 이동
		backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
		// 2 충돌 테스트를 위해서 test.png 로 잠시 바꿈 -> BackgroundPlayerService 로 이동
		// 10 배경 backgroundMapService 로 변경 -> player 로 이동
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
		
			@Override
			public void keyPressed(KeyEvent e) {	
				switch(e.getKeyCode()) {	
					case KeyEvent.VK_LEFT:	
						if(!player.isLeft()) {
							player.left();
						}
						break;				
					case KeyEvent.VK_RIGHT:	
						if(!player.isRight()) {	
							player.right();
						}
						break;				
					case KeyEvent.VK_UP:	
						if(!player.isUp() && !player.isDown()) {  
							player.up();
						}
						break;
				}
			}
			
			
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
