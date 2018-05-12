package com.bayviewglen.zork;

import java.util.ArrayList;

public class Inventory {
	private static ArrayList<Item> items;

	public Inventory() {
		items = new ArrayList<Item>();
	}

	public static void addItem(Item item) {
		items.add(item);
	}

	public static void removeItem(Item item) {
		items.remove(item);
	}

	public int howManyItems() {
		return items.size();
	}

	public static ArrayList<Item> getInventory(){
		return items;
	}
	
	public static Item getItem(int index){
		return items.get(index);
	}

}
