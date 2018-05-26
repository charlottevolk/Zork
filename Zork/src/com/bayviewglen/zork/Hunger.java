package com.bayviewglen.zork;

public class Hunger{

	private static final int maxLen = 10;
	private String[] statBar;

	public Hunger() {
		for(int i=0; i<statBar.length; i++) {
			statBar[i] = "*";
		}
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
	
	public void printHunger() {
		System.out.println("Hunger Bar:");
		for(int i=0; i<maxLen; i++) {
			System.out.print("|" + statBar[i] + "|");
		}
		System.out.println();
	}
}
