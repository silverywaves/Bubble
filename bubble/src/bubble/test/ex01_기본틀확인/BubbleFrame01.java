package bubble.test.ex01_기본틀확인;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class BubbleFrame01 extends JFrame{	// 2 - JFrame 참조 (윈도우 창 -> 내부에 패널을 하나 가지고 있음)

	
	// 1 - 생성자
	public BubbleFrame01() {
		setSize(1000,640);	// 3 - 프레임 크기 설정
		getContentPane().setLayout(null);	// 4 - absolute 레이아웃 (자유롭게 꾸밀 수 있음)
		
		// 9 - 이미지 삽입 
		// 9_1 - 작업중인 java(class)파일 우클릭->open with->windowBuilder Editor->Label 추가
		JLabel lblNewLabel = new JLabel("New label");	// 레이블 영역 생성 : 레이블->글자, 이미지 모두 삽입 가능
		lblNewLabel.setBounds(192, 195, 50, 15);		// 버튼의 위치 선정
		getContentPane().add(lblNewLabel);				// 프레임에서 컨텐츠 영역을 가져와서 거기다가 버튼을 붙임
		// 편리하지만 코드가 지저분해짐 -> ex02 에서는 직접 코드 작성한 걸로 진행
		
		
		setLocationRelativeTo(null);	// 7 - JFrame 중앙에 배치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// 8 - 종료버튼(x)으로 창을 끌 때 JVM 같이 종료
		setVisible(true);	// 5 - 화면에 프레임 출력	
	}
	
	
	public static void main(String[] args) {
		new BubbleFrame01();	// 6
	}
}
