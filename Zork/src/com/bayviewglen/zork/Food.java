package com.bayviewglen.zork;

public class Food extends Item{

	public Food(String type, String property) {
		super(type, property);
	}
	
	public void eat() {
		System.out.println("You ate the " + getDescription());
	}

}