package com.bayviewglen.zork;

public class Stats {
	private Hunger hunger;
	private Thirst thirst;
	
	public Stats() {
		hunger = new Hunger();
		thirst = new Thirst();
	}
	
	public Hunger getHunger() {
		return hunger;
	}
	
	public Thirst getThirst() {
		return thirst;
	}
	
	public void reduce() {
		hunger.reduce();
		thirst.reduce();
	}

}
