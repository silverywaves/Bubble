package bubble.test.ex02_배경및캐릭터삽입;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class BubbleFrame extends JFrame{	// 2 JFrame 참조 (윈도우 창 -> 내부에 패널을 하나 가지고 있음)

	private JLabel backgroundMap;	// 9-1 이미지 삽입
	private Player player;			// 10-1 player 붙여넣기
	
	// 1 생성자 추가
	public BubbleFrame() {
		initObject();		//
		initSetting();		// 9-5 initSetting 함수 호출 -> 정상실행 확인
		setVisible(true);	// 5 화면에 프레임 출력	
	}
	
	private void initObject() {		// 9-2 initObject 함수 생성
		backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));	// 9-6 backgroundMap 에 JLabel 이미지 메모리 생성 <new ImageIcon("경로")>  
		// 이미지 출력 x -why?-> 위치와 크기가 없어서! windowBuilderEditor 에 들어가서 backgroundMap-"안녕" 선택해보면 크기와 위치(Bounds)가 (0,0,0,0)
//		backgroundMap.setSize(1000,600);		// 9-8 크기 설정하면 정상출력 -> 정상 출력 확인을 위해 임의로 size 를 넣었지만
//												// JPanel = JLabel 인걸로 진행(굳이 3개층일 필요 없음)
//		getContentPane().add(backgroundMap);	// 9-7 JFrame 에 JLabel 을 붙임
//		// getContentPane : 큰 틀(JFrame)에 속해있는 실질적 도화지 역할을 하는 작은 틀(JPanel)을 호출하는 메서드
//		// design 들어가서 components 확인해보면 getContentPane() 안에 레이블(backgroundMap)이 속해있음
//		// 즉, 정확하게 얘기하면 JFrame 내의 JPanel 내에 JLabel 을 붙여 넣는 것
		setContentPane(backgroundMap);		// 9-9 JPanel 이 JLabel 로 변경(windowBuilder design 에서 확인 가능)
		
		player = new Player();	// 10-2 player 에 Player 메모리 생성
		add(player);			// 10-3 붙여넣기(패널 자체를 바꾸는 것이 아니라 추가로 붙여 넣을 것이므로 add 사용) => 캐릭터 출발위치(왼쪽하단)에 정상적으로 출력
		// 캐릭터 출발위치 좌표는 어떻게 잡으면 될까?
		// => windowBuilder design 에서 위치 이동시켜가면서 좌표 찾아냄
	}
	
	private void initSetting() {		// 9-3 initSetting 함수 생성
		// 9-4 JFrame 기본설정 내용 initSetting 함수로 옮기기
		setSize(1000,640);	// 3 프레임 크기 설정
		setLayout(null);	// 4 absolute 레이아웃 (자유롭게 꾸밀 수 있음)
		
		setLocationRelativeTo(null);	// 7 JFrame 중앙에 배치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// 8 - 종료버튼(x)으로 창을 끌 때 JVM 같이 종료
	}
	
	
	public static void main(String[] args) {
		new BubbleFrame();	// 6
	}
}
