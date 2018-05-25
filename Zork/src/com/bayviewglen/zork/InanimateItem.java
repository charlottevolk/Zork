package com.bayviewglen.zork;

import java.util.ArrayList;

public class InanimateItem extends Item {
	
	private static InanimateItem[] validInanimateItems;
	private Inventory itemInventory = new Inventory();

	public InanimateItem(String type, String property) {
		super(type, property);
	}

	public void open() {
		System.out.println("You opened the " + getDescription());
	}

	public static void setValidInanimateItems() {
		validInanimateItems = new InanimateItem[20];
		validInanimateItems[0] = new Book("");
		validInanimateItems[1] = new Book("1984");
		validInanimateItems[2] = new Box("");
		validInanimateItems[3] = new Box("wooden");

	}

	public static InanimateItem[] getValidInanimateItems() {
		return validInanimateItems;
	}

	public static boolean isInValidInanimateItems(Item item) {
		for(int i=0; i<validInanimateItems.length; i++) {
			if(item.getDescription().equals(validInanimateItems[i].getDescription())) {
				//item.getType() != null && item.getProperty() != null && 
				return true;
			}
		}
		return false;
	}

}
