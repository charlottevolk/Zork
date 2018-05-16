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
		for(int i=0; i<items.size(); i++) {
			if(item.getType().equalsIgnoreCase(items.get(i).getType()) && item.getProperty().equalsIgnoreCase(items.get(i).getProperty())) {
				items.remove(i);
			}
		}
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
