package com.bayviewglen.zork;

import java.util.ArrayList;

public class Inventory {
	private ArrayList<Item> items;
	
	public Inventory(ArrayList<Item> itemsInHand) {
		items = itemsInHand;
	}
	
	public void addItem(Item item) {
		items.add(item);
	}
	
	public void removeItem(Item item) {
		items.remove(item);
	}
	
	public int howManyItems() {
		return items.size();
	}
}
