package com.bayviewglen.zork;

public class Drink extends Item{

	public Drink(String type, String property) {
		super(type, property);
	}
	
	public void drink() {
		System.out.println("You drank the " + getDescription());
	}

}