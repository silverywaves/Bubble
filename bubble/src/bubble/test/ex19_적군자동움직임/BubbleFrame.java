package bubble.test.ex19_적군자동움직임;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter		
@Setter
public class BubbleFrame extends JFrame{
	
	private BubbleFrame mContext = this;	
	private JLabel backgroundMap;
	private Player player;
//	private List<Enemy> enemy; 		// 적군 여러마리 만들기
	private Enemy enemy;	
	
	
	public BubbleFrame() { 
		initObject();		
		initSetting();		
		initListener();		
		setVisible(true);
	}
	
	private void initObject() {		
		backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));

		setContentPane(backgroundMap);	
		
		player = new Player(mContext);	
		add(player);		
		
		enemy = new Enemy(mContext);
		add(enemy);	
		
		new BGM();
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
						player.attack();	
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
