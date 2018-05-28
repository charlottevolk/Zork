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
	
	public void play(boolean loop){
		File music = new File(file);
		
		try {
			clip = AudioSystem.getClip();
		
			clip.open(AudioSystem.getAudioInputStream(music));
			if(loop) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
			clip.start();
		} catch (Exception e) {
			throw new RuntimeException("Fatal error playing music", e);
		}
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
