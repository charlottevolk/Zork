package com.bayviewglen.zork;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicClip {
	
	private final String file;
	private Clip clip;
	
	public MusicClip(String file) {
		this.file = file;
	}
	
	public void play() throws Exception{
		File music = new File(file);
		
		clip = AudioSystem.getClip();
		clip.open(AudioSystem.getAudioInputStream(music));
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		clip.start();
	}
	
	public void stop() {
		if(clip != null) {
			clip.stop();
		}
	}

//	public static void main(String[]args) throws Exception{
//		final MusicClip clip = new MusicClip("./Sound/dark_world.wav");
//		
//		clip.play();
//		Thread.sleep(12000000);
//
//	}

}
