package com.bayviewglen.zork;

//import java.util.ArrayList;

public class InanimateItem extends Item {
	
	private static InanimateItem[] validInanimateItems;
	//private Inventory itemInventory = new Inventory();

	public InanimateItem(String type, String property) {
		super(type, property);
	}

	public void open() {
		System.out.println("You opened the " + getDescription());
	}

	public static void setValidInanimateItems() {
		validInanimateItems = new InanimateItem[20];
		validInanimateItems[0] = new Book("old");
		validInanimateItems[1] = new Book("red");
		validInanimateItems[2] = new Box("wooden");
		validInanimateItems[3] = new Box("steel");
		validInanimateItems[4] = new Container("plastic");
		validInanimateItems[5] = new Container("food");
		validInanimateItems[6] = new Box("black");
		validInanimateItems[7] = new Drawer("desk");
		validInanimateItems[8] = new Trunk("blue");
		validInanimateItems[9] = new Cabinet("kitchen");
		validInanimateItems[10] = new Box("metal");
		validInanimateItems[11] = new Box("engraved");
		validInanimateItems[12] = new Drawer("wardrobe");
		validInanimateItems[13] = new Jar("cookie");
		validInanimateItems[14] = new Jar("mason");

	}

	public static InanimateItem[] getValidInanimateItems() {
		return validInanimateItems;
	}

	public static boolean isInValidInanimateItems(Item item) {
		for(int i=0; i<validInanimateItems.length; i++) {
			if(item.getDescription().equals(validInanimateItems[i].getDescription())) {
				return true;
			}
		}
		return false;
	}

}
