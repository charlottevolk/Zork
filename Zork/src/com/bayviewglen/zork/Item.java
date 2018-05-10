package com.bayviewglen.zork;

public class Item {

	private String type;
	private String property;

	public Item(String type, String property) {
		this.type = type;
		this.property = property;
	}
	
	public void pickUpItem(Item item) {
		System.out.print("You picked up the ");
	}
	
	public void putDownItem(Item item) {
		System.out.print("You put down the ");
	}
	
	public void putInInventory(Item item) {
		Inventory.addItem(item);
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
/*
	public void setName(String name) {
		this.name = name;
	}
	*/	
}