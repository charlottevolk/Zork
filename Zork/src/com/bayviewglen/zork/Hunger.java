package com.bayviewglen.zork;

public class Hunger{

	private static final int maxLen = 15;
	private String[] statBar;

	public Hunger() {
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
		return false;
	}

	public boolean increase() {
		boolean result = false;
		for(int j=0; j<5; j++) {
			for(int i=0; i<maxLen; i++) {
				if(statBar[i].equals("X")) {
					statBar[i] = "*";
					i=100;
					result = true;
				}
			}
		}
		printHunger();
		return result;
	}

	public void printHunger() {
		System.out.println("Hunger Bar:");
		for(int i=0; i<maxLen; i++) {
			System.out.print("|" + statBar[i] + "|");
		}
		System.out.println();
	}
}
