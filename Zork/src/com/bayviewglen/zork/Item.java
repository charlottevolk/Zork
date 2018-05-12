package com.bayviewglen.zork;

import java.util.ArrayList;
public class Item {

	private String type;
	private String property;

	public Item(String type, String property) {
		this.type = type;
		this.property = property;
	}

	public void pickUpItem() {
		if(canPickUp())
			System.out.println("You picked up the " + getDescription());
		else
			System.out.println("You can't pick up the " + getDescription());
	}

	public void putDownItem() {
		if(!canPickUp())
			System.out.println("You can't put down the " + getDescription() + "... you aren't holding it!");
		else
			System.out.println("You put down the " + getDescription());
	}

	public boolean canPickUp() {
		if(getType().equalsIgnoreCase("apple")){
			return true;
		}
		else if(getType().equalsIgnoreCase("bread")) {
			return true;
		}
		else if(getType().equalsIgnoreCase("water")) {
			return true;
		}
		else
			return false;
	}
	
	public boolean canEat() {
		if(getType().equalsIgnoreCase("apple")){
			return true;
		}
		else if(getType().equalsIgnoreCase("bread")) {
			return true;
		}
		else
			return false;
	}
	
	public boolean canDrink() {
		if(getType().equalsIgnoreCase("water")){
			return true;
		}
		else
			return false;
	}

	public String getDescription() {
		return property + " " + type.toLowerCase();
	}

	public String getType() {
		return type;
	}

	public String getProperty() {
		return property;
	}
	
	
}