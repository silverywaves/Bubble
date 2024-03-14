package bubble.test.ex01_기본틀확인;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class BubbleFrame02 extends JFrame{	// 2 JFrame 참조 (윈도우 창 -> 내부에 패널을 하나 가지고 있음)

	private JLabel backgroundMap;	// 9-1 텍스트 삽입
	
	// 1 생성자 추가
	public BubbleFrame02() {
		initObject();		//
		initSetting();		// 9-5 initSetting 함수 호출 -> 정상실행 확인
		setVisible(true);	// 5 화면에 프레임 출력	
	}
	
	private void initObject() {		// 9-2 initObject 함수 생성
		backgroundMap = new JLabel("안녕");	// 9-6 backgroundMap 메모리 생성  <- 이 단계에서는 프레임에 글자 출력되지 않음
		backgroundMap.setSize(100, 100);		// 크기 설정 -> 정상 출력
		backgroundMap.setLocation(300, 300);	// 좌표 설정 -> 정상 출력
		add(backgroundMap);					// 9-7 JFrame 에 JLabel 을 붙임  <- 이 단계에서도 글자 출력되지 않음
		// -why?-> 위치와 크기가 없어서! windowBuilderEditor 에 들어가서 backgroundMap-"안녕" 선택해보면 크기와 위치(Bounds)가 (0,0,0,0)
		
	}
	
	private void initSetting() {		// 9-3 initSetting 함수 생성
		// 9-4 JFrame 기본설정 내용 initSetting 함수로 옮기기
		setSize(1000,640);	// 3 프레임 크기 설정
		getContentPane().setLayout(null);	// 4 absolute 레이아웃 (자유롭게 꾸밀 수 있음)
		
		setLocationRelativeTo(null);	// 7 JFrame 중앙에 배치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// 8 - 종료버튼(x)으로 창을 끌 때 JVM 같이 종료
	}
	
	
	public static void main(String[] args) {
		new BubbleFrame02();	// 6
	}
}
