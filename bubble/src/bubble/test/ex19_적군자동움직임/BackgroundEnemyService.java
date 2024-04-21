package bubble.test.ex19_적군자동움직임;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

// 2 PlayerService 참고하여 적절하게 내용 적어주기
public class BackgroundEnemyService implements Runnable {		

	 private BufferedImage image;
	 private Enemy enemy;
	 
	 // 플레이어, 버블 정보
	 public BackgroundEnemyService(Enemy enemy) {	
		 this.enemy = enemy;
	
		 try {
			 image = ImageIO.read(new File("image/backgroundMapService.png")); 
		 }catch(Exception e){
			 System.out.println(e.getMessage());
		 }
	 }
	
	@Override
	public void run() {	
		while(enemy.getState()==0) {	// enemy 가 물방울에 갇힌 상태(1)에서는 움직일 필요X
			
			// 벽 충돌 체크
			// 색상확인
			Color leftColor = new Color(image.getRGB(enemy.getX()-10, enemy.getY()+25));
			Color rightColor = new Color(image.getRGB(enemy.getX()+50+15, enemy.getY()+25));


			int bottomColor = image.getRGB(enemy.getX()+10, enemy.getY() + 50 + 5) 
					+ image.getRGB(enemy.getX()+50, enemy.getY() + 50 + 5);	
			// 바닥 충돌 확인
			if(bottomColor != -2) {	
				enemy.setDown(false); 
			} else {		
				if(!enemy.isUp() && !enemy.isDown()) {
					System.out.println("다운 실행");
					enemy.down();	
				}
			}
			
			
			// 외벽 충돌 확인
			if(leftColor.getRed() == 255 && leftColor.getGreen()==0 && leftColor.getBlue()==0) {
//				System.out.println("왼쪽 벽에 충돌함");
				enemy.setLeft(false); 		
				// 4-1. 왼쪽 충돌시 오른쪽으로 이동
				if(!enemy.isRight()) {	// 왼쪽벽에 충돌 후 적이 오른쪽으로 이동 중인지 여부를 반환 => 만약 적이 오른쪽으로 이동 중이 아니라면
					enemy.right();	// enemy.right() 호출 -> 적을 오른쪽으로 이동
				}
			} else if(rightColor.getRed()==255 && rightColor.getGreen()==0 && rightColor.getBlue()==0) {
//				System.out.println("오른쪽 벽에 충돌함");
				enemy.setRight(false);
				// 4-1. 오른쪽 충돌시 왼쪽으로 이동
				if(!enemy.isLeft()) {
					enemy.left();
				}
			} 
				
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
