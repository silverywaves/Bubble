package bubble.test.ex07_충돌정지;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class BackgroundPlayerService implements Runnable {		

	 private BufferedImage image;
	 private Player player;
	 

	 public BackgroundPlayerService(Player player) {
		 this.player = player;		
	
		 try {
			 image = ImageIO.read(new File("image/backgroundMapService.png")); 
		 }catch(Exception e){
			 System.out.println(e.getMessage());
		 }
	 }
	
	@Override
	public void run() {	
		while(true) {
			
			Color leftColor = new Color(image.getRGB(player.getX()-10, player.getY()+25));
			Color rightColor = new Color(image.getRGB(player.getX()+50+15, player.getY()+25));

			if(leftColor.getRed() == 255 && leftColor.getGreen()==0 && leftColor.getBlue()==0) {
				System.out.println("왼쪽 벽에 충돌함");
				player.setLeftWallCrash(true);	// 5 충돌했을때
				player.setLeft(false); 			// 4 더이상 움직일 수 없게 만들어주자
				// => 한번 충돌한 이후 왼쪽으로 다시 갈 수 없는 문제 발생 <- Crash 가 계속 true 이기 때문에
				// => 6 충돌한 상태가 아닐 때, 충돌상태를 모두 false 로 바꿔줘야 함.
			} else if(rightColor.getRed()==255 && rightColor.getGreen()==0 && rightColor.getBlue()==0) {
				System.out.println("오른쪽 벽에 충돌함");
				player.setRightWallCrash(true);
				player.setRight(false);
			} else {		// 6 충돌상태 x
				player.setLeftWallCrash(false);
				player.setRightWallCrash(false);
			}
				
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
