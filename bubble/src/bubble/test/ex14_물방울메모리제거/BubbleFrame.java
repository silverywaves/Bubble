package bubble.test.ex14_물방울메모리제거;

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
	
	private BubbleFrame mContext = this;	// 5-11 
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
					case KeyEvent.VK_SPACE:	
//						Bubble bubble = new Bubble(this);	// 5-10	this가 initListener로 인식되기때문에 오류발생 -> BubbleFrame 으로 재설정
						Bubble bubble = new Bubble(mContext);	// 5-12 Bubble 로 이동
						add(bubble);		
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
