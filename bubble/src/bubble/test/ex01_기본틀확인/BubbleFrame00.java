package bubble.test.ex01_기본틀확인;

import javax.swing.JFrame;
import javax.swing.JButton;

// < 하얀 도화지 위에 그림을 그린다고 생각해보자!>
public class BubbleFrame00 extends JFrame{	// 1 JFrame 참조 (윈도우 창 -> 내부에 패널을 하나 가지고 있음)
	
	
	public BubbleFrame00() {		// 2 생성자
		setSize(1000, 640);		// 4 프레임 크기 설정

		// help->eclipse MarketPlace->windowBuilder 다운로드
		// 6~7 java 파일 우클릭->open with->windowBuilder Editor->design 탭에서 레이아웃 변경 및 버튼 생성
		getContentPane().setLayout(null);	// 6 absolute layout : 배치 관리자가 없는 컨테이너(패널을 내가 원하는대로 꾸밀 수 있음)
		
		JButton btnNewButton = new JButton("1111");	// 7-1 1111 이라는 버튼 영역 생성
		btnNewButton.setBounds(80, 77, 93, 23);		// 7-2 버튼의 위치 선정
		getContentPane().add(btnNewButton);			// 7-3 프레임에서 컨텐츠 영역을 가져와서 거기다가 버튼을 붙임
		
		setVisible(true);		// 5 화면에 프레임 출력	
	}

	public static void main(String[] args) {
		new BubbleFrame00();						// 3
	}
}
