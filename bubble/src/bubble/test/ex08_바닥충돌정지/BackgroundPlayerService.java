package bubble.test.ex08_바닥충돌정지;

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
			// 2 착지 색상 확인
			int bottomColor = image.getRGB(player.getX()+10/*가장 왼쪽+여유공간*/, player.getY() + 50 + 5/*가장 하단+여유공간*/)  // 캐릭터 크기가 50x50
//			System.out.println("바텀컬러 : " + bottomColor);
			// 빨간색 바닥일 때 : -65526 / 빈공간 점프했을 때 : -1 / 파란색 바닥에 닿았을 때 : -16776961? 출력됨
			// => -1 이 아니면, 빨간색 바닥 혹은 파란색 바닥이라는 뜻
					+ image.getRGB(player.getX()+50/*가장 오른쪽*/, player.getY() + 50 + 5/*하단*/);	// 7 가장 오른쪽 하단 내용 추가
			// => bottomColor 의 값이 -2 라면 둘 다 -1(하얀색)=> 바닥에 색깔이 없응
			
			// 3 바닥 충돌 확인
			if(bottomColor != -2) {		// 8 bottomColor != -1 에서 -2 로 바꿔줌 -> 캐릭터 끄트머리에서도 착지 문제 없음 -> 버그 문제 해결 -> BubbleFrame 으로 돌아가서 배경이미지 다시 수정
//				System.out.println("바텀컬러 : " + bottomColor);
//				System.out.println("바닥에 충돌함");
				player.setDown(false);    // 4 player 조건문 수정하러 가기
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
