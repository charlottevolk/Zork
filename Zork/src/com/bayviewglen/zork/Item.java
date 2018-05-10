package com.bayviewglen.zork;

public abstract class Item {

	private String name;

	public Item(String name) {
		this.name = name;
	}
	
	public abstract void pickUpItem(Item item);
	
	public abstract void putDownItem(Item item);
	
	public abstract void putInInventory(Item item);
}