package com.bayviewglen.zork;

public class ItemsInGame {
	private static final String[] validItems = {"apple", "banana", "book", "box", "bread", "cabbage", "cabinet", "cheese", "chocolate", "coffee", "container", "drawer", "fish", "juice", "liquid", "muffin", "milk", "pasta", "stew", "trunk", "water", "tea"};
	
	public static boolean isInGame(String item) {
		for(int i=0; i<validItems.length; i++) {
			if(item != null && item.equalsIgnoreCase(validItems[i]))
				return true;
		}
		return false;
	}
}