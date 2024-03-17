package bubble.test.ex05_점프;

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
						if(!player.isUp() && !player.isDown()) {  // 7 점프중, 다운중이 아닌 경우에만 실행하도록 조건문 걸어줌
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
