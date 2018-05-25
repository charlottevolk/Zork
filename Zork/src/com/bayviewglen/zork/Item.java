package com.bayviewglen.zork;

public class Item {

	private String type;
	private String property;

	public Item(String type, String property) {
		this.type = type;
		this.property = property;
	}

	public void pickUpItem() {
		if(canPickUp())
			if(getType().equalsIgnoreCase("water") && getProperty().equalsIgnoreCase("glass")){
				System.out.println("You picked up the " + getProperty() + " of " + getType());
			}
			else{
				System.out.println("You picked up the " + getDescription());
			}
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
		else if(getType().equalsIgnoreCase("chocolate")) {
			return true;
		}
		else if(getType().equalsIgnoreCase("cheese")) {
			return true;
		}
		else if(getType().equalsIgnoreCase("bread")) {
			return true;
		}
		else if(getType().equalsIgnoreCase("water")) {
			return true;
		}
		else if(getType().equalsIgnoreCase("stew")) {
			return true;
		}
		else if(getType().equalsIgnoreCase("coffee")) {
			return true;
		}
		else if(getType().equalsIgnoreCase("book")) {
			return true;
		}
		else if(getType().equalsIgnoreCase("box")) {
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
		else if(getType().equalsIgnoreCase("stew")) {
			return true;
		}
		else if(getType().equalsIgnoreCase("chocolate")) {
			return true;
		}
		else if(getType().equalsIgnoreCase("cheese")) {
			return true;
		}
		else
			return false;
	}
	
	public boolean canDrink() {
		if(getType().equalsIgnoreCase("water")){
			return true;
		}
		else if(getType().equalsIgnoreCase("coffee")) {
			return true;
		}
		else
			return false;
	}
	
	public boolean canOpen() {
		if(getType().equalsIgnoreCase("book")) {
			return true;
		}
		else if(getType().equalsIgnoreCase("box")) {
			return true;
		}
		else {
			return false;
		}
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
