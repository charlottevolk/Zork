package com.bayviewglen.zork;

public class ItemsInGame {
	private static final String[] validItems = {"apple", "bread", "water", "stew", "coffee", "cheese", "chocolate", "book", "box"};
	
	public static boolean isInGame(String item) {
		for(int i=0; i<validItems.length; i++) {
			if(item != null && item.equalsIgnoreCase(validItems[i]))
				return true;
		}
		return false;
	}
}