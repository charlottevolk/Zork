package com.bayviewglen.zork;

public class StatsDriver {

	public static void main(String[] args) {
		Stats stats = new Stats();
		stats.getHunger().printHunger();
		stats.getThirst().printThirst();
		stats.getHunger().reduce();
		stats.getHunger().reduce();
		stats.getHunger().reduce();
		stats.getHunger().reduce();
		stats.getHunger().reduce();
		stats.getHunger().reduce();
		stats.getHunger().reduce();
		stats.getHunger().reduce();
		stats.getHunger().reduce();
		stats.getHunger().reduce();
		stats.getHunger().reduce();
		stats.getHunger().printHunger();
	}

}
