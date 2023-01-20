package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class sound {

	Clip clip;
	URL soundUrl[] = new URL[10];

	public sound() {

		soundUrl[0] = getClass().getResource("/sound/BGtrack (1).wav");
		soundUrl[1] = getClass().getResource("/sound/coin.wav");

	}

	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrl[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);

		} catch (Exception e) {

		}
	}

	public void play() {

		clip.start();
	}

	public void loop() {

		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stop() {

		clip.stop();
	}
}
