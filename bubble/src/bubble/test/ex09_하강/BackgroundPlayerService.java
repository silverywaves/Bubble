package bubble.test.ex09_하강;

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


			int bottomColor = image.getRGB(player.getX()+10, player.getY() + 50 + 5) 
					+ image.getRGB(player.getX()+50, player.getY() + 50 + 5);	
			// 바닥 충돌 확인
			if(bottomColor != -2) {	
				player.setDown(false); 	// 1-1 멈출때는 true/false 로 스레드를 제어하지만, 
			} else {		// 3 현재 else 는 -2 일때 실행됨 => 바닥색깔이 하얀색이라는 뜻
//				if(!player.isUp()) {	// 4 점프상태가 아닐 경우에 실행하라는 조건문 걸어주기 -> 버그발생!! 빈공간 점프시 캐릭터 사라질정도로 하강함
					// 발생원인 : backgroundPlayerService 는 계속 실행이 되는데 그동안 바닥도 계속 확인함. 
					// -> 바닥이 아무것도 없으면 player.down() 메서드를 무한 실행
					// 5 down 이 false 인 상태에서만(player가 다운이 아닐때) 실행될 수 있게 조건을 다시 걸어줌
				if(!player.isUp() && !player.isDown()) {
					System.out.println("다운 실행");	// 2-2 어떻게 작동하는지 출력시켜보면 결국 점프할때마다 down() 실행되기 때문에 점프가 안되는 것
					player.down();	// 1-2 다시 실행할때는 player.down() 으로 호출해줘야함
				}
				// 2-1 호출한 후에는 점프가 안됨 -why?-> 점프를 하는 순간 bottomColor가 -2 가 아니게 됨
			}
			
			
			// 외벽 충돌 확인
			if(leftColor.getRed() == 255 && leftColor.getGreen()==0 && leftColor.getBlue()==0) {
//				System.out.println("왼쪽 벽에 충돌함");
				player.setLeftWallCrash(true);	
				player.setLeft(false); 		
			} else if(rightColor.getRed()==255 && rightColor.getGreen()==0 && rightColor.getBlue()==0) {
//				System.out.println("오른쪽 벽에 충돌함");
				player.setRightWallCrash(true);
				player.setRight(false);
			} else {		
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
