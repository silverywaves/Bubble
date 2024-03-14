package bubble.test.ex02_배경및캐릭터삽입;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

// 캐릭터(플레이어)를 만들기


public class Player extends JLabel{		// 1 JLabel 참조 
	private int x;	// 2 x 좌표 선언
	private int y;	// 3 y 좌표 선언
	
	// 6 플레이어 이미지 삽입
	private ImageIcon playerR, playerL;	// 6-3 플레이어 캐릭터의 오른쪽이미지, 왼쪽이미지 추출
	
	// 4 생성자
	public Player() {
		initObject();
		initSetting();
	}
	
	// 5 initObject, initSetting 함수 생성
	private void initObject() {
		playerR = new ImageIcon("image/playerR.png");	// 6-1 playerR 에 playerR 이미지 메모리 생성(캐릭터의 오른쪽 이미지)
		playerL = new ImageIcon("image/playerL.png");	// 6-2 playerL 에 playerL 이미지 메모리 생성(캐릭터의 왼쪽 이미지)
	}
	
	private void initSetting() {
		x = 55;		// 6-4 x 좌표 값 할당(초기화)
		y = 535;	// 6-5 y 좌표 값 할당(초기화)
		
		// BubbleFrame 에서 PLabel 에 이미지를 넣었음 => Player 자체가 레이블
		setIcon(playerR);	// 6-6 오른쪽 이미지 삽입 / = this.setIcon(playerR);  <- 현재 자기자신 위치이므로 this. 는 생략 가능
		setSize(50,50);		// 6-7 캐릭터 사이즈 설정
		setLocation(x,y);	// 6-8 x, y 좌표 적용
		// 여기까지 완료했으면 -> 7. BubbleFrame 에 Player 붙여넣기 (BubbleFrame 으로 이동)

	}
}
