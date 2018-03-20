package rts;

public class AlarmSystem {

	public void setAlarm(String s){
		Float val = Float.parseFloat(s.substring(1)) ;
		if(val < 70){
			AudioPlayer02 audioplayer02 = new AudioPlayer02();
			String song="C:\\Users\\hp\\Desktop\\rec_62s.wav";
			audioplayer02.playAudio(song);
		}
	}
}
