package com.bayviewglen.zork;

public class ItemsInGame {
	private static final String[] validItems = {"apple", "bread", "water"};
	
	public static boolean isInGame(String item) {
		for(int i=0; i<validItems.length; i++) {
			if(item.equalsIgnoreCase(validItems[i]))
				return true;
		}
		return false;
	}
}
