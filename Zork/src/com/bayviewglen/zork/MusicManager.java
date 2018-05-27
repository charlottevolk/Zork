package com.bayviewglen.zork;

import java.util.HashMap;
import java.util.Map;

public class MusicManager {
	private Map<String, MusicClip> clips = new HashMap<>();
	private MusicClip current;
	
	public void playClip(String name) throws Exception{
		MusicClip musicClip = clips.get(name);
		if(musicClip == null) {
			musicClip = new MusicClip(name);
			clips.put(name, musicClip);
		}
		 stopCurrent();
		 current = musicClip;
		 musicClip.play();
	}
	
	public void stopPlaying() {
		stopCurrent();
	}
	
	private void stopCurrent() {
		if (current != null) {
			current.stop();
		}
	}
}
