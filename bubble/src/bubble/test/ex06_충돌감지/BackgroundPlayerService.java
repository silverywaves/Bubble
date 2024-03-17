package bubble.test.ex06_충돌감지;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

// 메인스레드는 키보드 이벤트를 처리한다고 바쁨
// -> 백그라운드에서 계속 관찰
public class BackgroundPlayerService implements Runnable {		// 1-1 Runnable
	// 3 test 이미지를 버퍼로 읽어서 테스트 해보기
	 private BufferedImage image;
	 // 7-1 플레이어 정보 필요(컴포지션)
	 private Player player;
	 
	 // 4 생성자 만들기
	 public BackgroundPlayerService(Player player) {	// 7-2
		 this.player = player;		// 7-3 BubbleFrame 이동
		 // 5 try catch
		 try {
			 image = ImageIO.read(new File("image/backgroundMapService.png")); // 9-1 충돌 확인했으면 배경이미지 바꿔주기
		 }catch(Exception e){
			 System.out.println(e.getMessage());
		 }
	 }
	
	@Override
	public void run() {		// 1-2	-> BubbleFrame 이동
		while(true) {
			// 충돌 관련 좌표보다 색상으로 처리하는게 코드짜기가 더 편리함
			// 6 색상 확인(x,y 좌표는 스퀘어로 생각했을때 왼쪽 위 꼭지점 -> y좌표 : 현재 캐릭터크기가 50이니까 중간자리면 /2 해서 25)
			Color leftColor = new Color(image.getRGB(player.getX()-10, player.getY()+25));
			Color rightColor = new Color(image.getRGB(player.getX()+50+15, player.getY()+25));
//			System.out.println("leftColor : " + leftColor);
//			System.out.println("rightColor : " + rightColor);
			// 9-2 충돌 확인했으면 bubbleFrame 이동해서 배경을 backgroundMap 으로 바꿔줌
			if(leftColor.getRed() == 255 && leftColor.getGreen()==0 && leftColor.getBlue()==0) {
				System.out.println("왼쪽 벽에 충돌함");
			} else if(rightColor.getRed()==255 && rightColor.getGreen()==0 && rightColor.getBlue()==0) {
				System.out.println("오른쪽 벽에 충돌함");
			}
				
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
