package bubble.test.ex17_적군물방울터트리기;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;


public class BackgroundPlayerService implements Runnable {		

	 private BufferedImage image;
	 private Player player;
	 private List<Bubble> bubbleList;	// 여러개의 버블 => List
	 
	 // 1. 플레이어, 버블 정보 필요 -> Player.java 이동
//	 public BackgroundPlayerService(Player player) {
//		 this.player = player;		
	 public BackgroundPlayerService(BubbleFrame mContext) {
		 this.player = mContext.getPlayer();
		 this.bubbleList = mContext.getPlayer().getBubbleList();	// 플레이어가 버블 생성
		 //  java.lang.NullPointerException 에러 발생 -why?->
	
		 try {
			 image = ImageIO.read(new File("image/backgroundMapService.png")); 
		 }catch(Exception e){
			 System.out.println(e.getMessage());
		 }
	 }
	
	@Override
	public void run() {	
		while(true) {
			
			// 6. 버블 충돌 체크
			for(int i=0; i<bubbleList.size();i++) {
				if(bubbleList.get(i).getState()==1) {	// 충돌을 했을 때
					if((Math.abs(player.getX() - bubbleList.get(i).getX()) < 10) 
							&& (Math.abs(player.getY() - bubbleList.get(i).getY()) > 0 && Math.abs(player.getY() - bubbleList.get(i).getY()) < 50)) {
						System.out.println("적군 터트리기 완료");
					}
				}
			}
			
			// 7. 벽 충돌 체크
			
			// 색상확인
			Color leftColor = new Color(image.getRGB(player.getX()-10, player.getY()+25));
			Color rightColor = new Color(image.getRGB(player.getX()+50+15, player.getY()+25));


			int bottomColor = image.getRGB(player.getX()+10, player.getY() + 50 + 5) 
					+ image.getRGB(player.getX()+50, player.getY() + 50 + 5);	
			// 바닥 충돌 확인
			if(bottomColor != -2) {	
				player.setDown(false); 
			} else {		
				if(!player.isUp() && !player.isDown()) {
					System.out.println("다운 실행");
					player.down();	
				}
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
