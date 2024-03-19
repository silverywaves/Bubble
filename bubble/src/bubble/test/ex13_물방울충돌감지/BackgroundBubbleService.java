package bubble.test.ex13_물방울충돌감지;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

// 1 물방울이 발사될때마다 backgroundPlayerService 실행 -> 비효율적 -> 버불백그라운드 생성
//   BackgroundPlayerService 에서 속성,매서드만 복사해옴 -> Player player 를 Bubble bubble 로 수정

public class BackgroundBubbleService {	// 2 Runnable x(스레드가 돌면 안됨)	
	// 4 BackgroundBubbleService : 한번 발동하는 것이 아니라 버블이 움직일때마다 벽이 있는지 확인 -> 버블이 만들어질때마다 new 가 되어야 함
	// 버블이 만들어지는 시점 => bubbleFrame 의 VK_SPACE => 의존성 컴포지션 추가해야함 Bubble 로 이동

	 private BufferedImage image;
	 private Bubble bubble;
	 

	 public BackgroundBubbleService(Bubble bubble) {
		 this.bubble = bubble;		
	
		 try {
			 image = ImageIO.read(new File("image/backgroundMapService.png")); 
		 }catch(Exception e){
			 System.out.println(e.getMessage());
		 }
	 }
	 
	 // 3 좌우 벽, 위쪽 벽에 물방울이 부딪혔을때 멈추는 메서드 생성
	 public boolean leftWall(){
		 // 10-1 버블 위치 색상 확인(왼쪽)
		 Color leftColor = new Color(image.getRGB(bubble.getX()-10, bubble.getY()+25));
		 // 11-1 충돌 확인(왼쪽)
		 if(leftColor.getRed() == 255 && leftColor.getGreen()==0 && leftColor.getBlue()==0) {
//				System.out.println("왼쪽 벽에 충돌함");
//			 bubble.setLeft(false); 	// 11-3 상태를 false로 만들 수 없음
			 // -why?-> while 이 아니라 for 문을 사용했기 때문에 
			 return true;	// 11-4 -> return true 만 해주면 break 되어 for문 빠져나감
		 } 		 
		 return false;
	 }
	 public boolean rightWall(){
		// 10-2 버블 위치 색상 확인(오른쪽)
		 Color rightColor = new Color(image.getRGB(bubble.getX()+50+15, bubble.getY()+25));
		// 11-2 충돌 확인(오른쪽)
		 if(rightColor.getRed()==255 && rightColor.getGreen()==0 && rightColor.getBlue()==0) {
//				System.out.println("오른쪽 벽에 충돌함");
			 return true;		// 11-4
			}

		 return false;
	 }
	 public boolean topWall(){
		 // 12-1 버블 위치 색상 확인(위쪽)
		 Color topColor = new Color(image.getRGB(bubble.getX()+25, bubble.getY()-10));	// 12-2 x 좌표는 버블의 중앙, y 좌표는 버블의 족즘 위쪽(여유공간)으로 설정
		 // 13 충돌 확인(위쪽) -> 14 Bubble 로 이동해서 위쪽벽에 충돌시 멈추는 코드 넣어주기
		 if(topColor.getRed()==255 && topColor.getGreen()==0 && topColor.getBlue()==0) {
//				System.out.println("위쪽 벽에 충돌함");
			 return true;		
			}
		 return false;
	 }
	
}
