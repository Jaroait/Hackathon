
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;

public class Sound {

	long pauseTime;
	Clip clip;
	Clip clip2;
	Clip clip3;
	Clip clip4;
	Clip clip5;
	Clip clip6;
	Clip clip7;
	Clip clip8;
	Clip clip9;
	Clip clip0;
	Clip clip11;
	Clip clip12;
	Clip clip13;
	Clip clip14;
	Clip clip15;

	FloatControl gainControl;
	FloatControl gainControl2;
	FloatControl gainControl3;
	FloatControl gainControl4;
	FloatControl gainControl5;
	FloatControl gainControl6;
	FloatControl gainControl7;
	FloatControl gainControl8;
	FloatControl gainControl9;
	FloatControl gainControl0;
	FloatControl gainControl11;
	FloatControl gainControl12;
	FloatControl gainControl13;
	FloatControl gainControl14;
	FloatControl gainControl15;
	
	AudioInputStream ais;
	boolean playing;
	boolean isPlayingtheme;
	boolean isPlayingEpic = false;
	boolean isMad = false;
	boolean isLaughing = false;
	boolean isDying = false;
	boolean isDrop = false;

	public Sound() {
		pauseTime = 0;
		playing = false;

	}

	public void playSong(String Filename) {
		try {

			File f = new File(Filename);
			clip = AudioSystem.getClip();
			clip2 = AudioSystem.getClip();
			clip3 = AudioSystem.getClip();
			clip4 = AudioSystem.getClip();
			clip5 = AudioSystem.getClip();
			clip6 = AudioSystem.getClip();
			clip7 = AudioSystem.getClip();
			clip8 = AudioSystem.getClip();
			clip9 = AudioSystem.getClip();
			clip0 = AudioSystem.getClip();
			clip11 = AudioSystem.getClip();
			clip12 = AudioSystem.getClip();
			clip13 = AudioSystem.getClip();
			clip14 = AudioSystem.getClip();
			clip15 = AudioSystem.getClip();

			ais = AudioSystem.getAudioInputStream(f);

			if (Filename == "freeze.wav") { // Sound for ice ball
				clip.open(ais);
				gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(-7.0f);
				clip.start();
			} else if (Filename == "fireballSound.wav") { // Sound for fire ball
				clip3.open(ais);
				gainControl3 = (FloatControl) clip3.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl3.setValue(-7.0f);
				clip3.start();
			} else if (Filename == "clicked.wav") { // Sound for clicked buttons
				clip4.open(ais);
				gainControl4 = (FloatControl) clip4.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl4.setValue(-1.0f);
				clip4.start();
			} else if (Filename == "switch.wav") { // Sound for weapon switch
				clip5.open(ais);
				gainControl5 = (FloatControl) clip5.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl5.setValue(-1.0f);
				clip5.start();
			} else if (Filename == "Song.wav") { // Sound for song
				isPlayingEpic = false;
				clip0.stop();
				clip6.open(ais);
				gainControl6 = (FloatControl) clip6.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl6.setValue(-8.0f);
				clip6.loop(Clip.LOOP_CONTINUOUSLY);
				isPlayingtheme = true;
			} else if (Filename == "rain_.wav") { // Sound for rain
				clip7.open(ais);
				gainControl7 = (FloatControl) clip7.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl7.setValue(-4.0f);
				clip7.loop(Clip.LOOP_CONTINUOUSLY);
				playing = true;
			} else if (Filename == "running.wav") { // Sound for running
				clip8.open(ais); // clip for song
				gainControl8 = (FloatControl) clip8.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl8.setValue(-5.0f);
				clip8.start();
			} else if (Filename == "lightningSound.wav") { // Sound for
				clip9.open(ais); // clip for song
				gainControl9 = (FloatControl) clip9.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl9.setValue(-1.0f);
				clip9.start();
			} else if (Filename == "epicSong.wav") { // Sound for song
				isPlayingEpic = true;
				clip6.stop();
				clip0.open(ais);
				gainControl0 = (FloatControl) clip0.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl0.setValue(-8.0f);
				clip0.loop(Clip.LOOP_CONTINUOUSLY);
				isPlayingtheme = false;
			} else if (Filename == "howl.wav") { // Sound for song
				clip11.open(ais);
				gainControl11 = (FloatControl) clip11.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl11.setValue(-15.0f);
				clip11.start();

			} else if (Filename == "mad.wav") { // Sound for song
				isMad = true;
				clip12.open(ais);
				clip12.start();
			} else if (Filename == "deepLaugh.wav") { // Sound for song
				isLaughing = true;
				clip13.open(ais);
				clip13.start();
			} else if (Filename == "dying.wav") { // Sound for song
				isDying = true;
				clip14.open(ais);
				gainControl14 = (FloatControl) clip14.getControl(FloatControl.Type.MASTER_GAIN);
				clip14.start();
			} else if (Filename == "drop.wav") { // Sound for song
				isDrop = true;
				clip15.open(ais);
				gainControl15 = (FloatControl) clip15.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl15.setValue(-1.0f);
				clip15.start();
			}else {
				clip.open(ais);
				clip.start();
			}
		} catch (Exception exception) {
			System.out.println("Failed To Play The WAV File!");
		}
	}

	public Boolean isPlaying() {
		return playing;
	}

	public void stopSong(String filename) {
		if (filename == "Song.wav") {
			clip6.stop();
		} else if (filename == "epicSong.wav") {
			clip0.stop();
		}

	}
}