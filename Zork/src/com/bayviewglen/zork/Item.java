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
		if(canPutDown())
			if(getType().equalsIgnoreCase("water") && getProperty().equalsIgnoreCase("glass")){
				System.out.println("You put down the " + getProperty() + " of " + getType());
			}
			else{
				System.out.println("You put down the " + getDescription());
			}
		else
			System.out.println("You can't put down the " + getDescription() + ", you can't even pick it up in the first place!!");
	}

	public boolean canPickUp() {
		if(getType().equalsIgnoreCase("apple")){
			return true;
		}
		else if(getType().equalsIgnoreCase("banana")) {
			return true;
		}
		else if(getType().equalsIgnoreCase("book")) {
			return true;
		}
		else if(getType().equalsIgnoreCase("box")) {
			return true;
		}
		else if(getType().equalsIgnoreCase("bread")) {
			return true;
		}
		else if(getType().equalsIgnoreCase("cabbage")) {
			return true;
		}
		else if(getType().equalsIgnoreCase("cheese")) {
			return true;
		}
		else if(getType().equalsIgnoreCase("chocolate")) {
			return true;
		}
		else if(getType().equalsIgnoreCase("coffee")) {
			return true;
		}
		else if(getType().equalsIgnoreCase("container")) {
			return true;
		}
		else if(getType().equalsIgnoreCase("liquid")) {
			return true;
		}
		else if(getType().equalsIgnoreCase("stew")) {
			return true;
		}
		else if(getType().equalsIgnoreCase("trunk")) {
			return true;
		}
		else if(getType().equalsIgnoreCase("water")) {
			return true;
		}
		else
			return false;
	}
	
	public boolean canPutDown() {
		return canPickUp();
	}
	
	
	public boolean canEat() {
		if(getType().equalsIgnoreCase("apple")){
			return true;
		}
		else if(getType().equalsIgnoreCase("banana")) {
			return true;
		}
		else if(getType().equalsIgnoreCase("bread")) {
			return true;
		}
		else if(getType().equalsIgnoreCase("cabbage")) {
			return true;
		}
		else if(getType().equalsIgnoreCase("cheese")) {
			return true;
		}
		else if(getType().equalsIgnoreCase("chocolate")) {
			return true;
		}
		else if(getType().equalsIgnoreCase("stew")) {
			return true;
		}
		else
			return false;
	}
	
	public boolean canDrink() {
		if(getType().equalsIgnoreCase("coffee")) {
			return true;
		}
		else if(getType().equalsIgnoreCase("water")){
			return true;
		}
		else if(getType().equalsIgnoreCase("liquid")) {
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
		else if(getType().equalsIgnoreCase("cabinet")) {
			return true;
		}
		else if(getType().equalsIgnoreCase("container")) {
			return true;
		}
		else if(getType().equalsIgnoreCase("drawer")) {
			return true;
		}
		else if(getType().equalsIgnoreCase("trunk")) {
			return true;
		}
		else {
			return false;
		}
	}

	public String getDescription() {
		return property.toLowerCase() + " " + type.toLowerCase();
	}

	public String getType() {
		return type.toLowerCase();
	}

	public String getProperty() {
		return property.toLowerCase();
	}
	
	
}
