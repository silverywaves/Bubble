package bubble.test.ex18_BGM넣기;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class BGM {
	
	// 생성자
	public BGM() {
		try {
			// sound/bgm.wav" 경로의 오디오 파일을 읽어 AudioInputStream 객체 생성
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sound/bgm.wav"));
			Clip clip = AudioSystem.getClip();	// File을 통해 Clip 생성 
			clip.open(ais);	// 생성한 Clip 객체에 AudioInputStream 연결 -> 오디오 파일 로드
			
			// 소리 설정(Clip의 제어를 위한 FloatControl 객체 가져오기)
			FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			
			// 볼륨 조정(-30dB)
			gainControl.setValue(-30.0f);
			
			clip.start();// clip start
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// -> BubbleFrame 의 initObject()에서 BGM 띄어주기

}
