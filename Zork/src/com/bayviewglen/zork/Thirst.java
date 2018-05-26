package com.bayviewglen.zork;

public class Thirst{
	
	private static final int maxLen = 10;
	private String[] statBar;

	public Thirst() {
		for(int i=0; i<statBar.length; i++) {
			statBar[i] = "*";
		}
	}
	
	public void printThirst() {
		System.out.println("Thirst Bar:");
		for(int i=0; i<maxLen; i++) {
			System.out.print("|" + statBar[i] + "|");
		}
		System.out.println();
	}
	
	public void reduce() {
		for(int i=0; i<maxLen; i++) {
			if(statBar[i].equals("X")) {
				if(i!=maxLen-1) {
					statBar[i+1] = "X";
				}
			}
		}
	}
}
