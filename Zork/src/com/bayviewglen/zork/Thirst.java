package com.bayviewglen.zork;

public class Thirst{
	
	private static final int maxLen = 10;
	private String[] statBar;

	public Thirst() {
		statBar = new String[maxLen];
		for(int i=0; i<maxLen; i++) {
			statBar[i] = "*";
		}
	}
	
	public boolean reduce() {
		if(statBar[maxLen-1].equals("*")) {
			statBar[maxLen-1] = "X";
			return true;
		}else {
			for(int i=0; i<maxLen; i++) {
				if(statBar[i].equals("X")) {
					if(i != 0) {
						statBar[i-1] = "X";
						return true;
					}else {
						return false;
					}
				}
			}
		}
		statBar[0] = "X";
		return true;
	}
	
	public boolean increase() {
		for(int i=0; i<maxLen; i++) {
			if(statBar[i].equals("X")) {
				statBar[i] = "*";
				printThirst();
				return true;
			}
		}
		printThirst();
		return false;
	}
	
	public void printThirst() {
		System.out.println("Thirst Bar:");
		for(int i=0; i<maxLen; i++) {
			System.out.print("|" + statBar[i] + "|");
		}
		System.out.println();
	}
	
}
