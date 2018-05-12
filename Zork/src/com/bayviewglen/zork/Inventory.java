package com.bayviewglen.zork;

import java.util.ArrayList;

public class Inventory {
	private ArrayList<Item> items;

	public Inventory() {
		items = new ArrayList<Item>();
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

	public ArrayList<Item> getInventory(){
		return items;
	}
	
	public Item getItem(int index){
		return items.get(index);
	}

}
