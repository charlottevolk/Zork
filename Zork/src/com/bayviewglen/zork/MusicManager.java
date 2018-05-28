package com.bayviewglen.zork;

import java.util.HashMap;
import java.util.Map;

public class MusicManager {
	private Map<String, MusicClip> clips = new HashMap<>();
	private MusicClip currentClip;
	private String currentName;
	
	public void playClip(String name){
		if(name.equalsIgnoreCase(currentName)) {
			return;
		}
		MusicClip musicClip = clips.get(name);
		
		if(musicClip == null) {
			musicClip = new MusicClip("./Sound/" + name);
			clips.put(name, musicClip);
		}
		 stopCurrent();
		 currentClip = musicClip;
		 currentName = name;
		 musicClip.play(true);
	}
	
	public void stopPlaying() {
		stopCurrent();
	}
	
	private void stopCurrent() {
		if (currentClip != null) {
			currentClip.stop();
		}
	}
}
