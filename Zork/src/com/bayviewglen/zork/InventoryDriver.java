package com.bayviewglen.zork;

public class InventoryDriver {
	public static void main(String[] args) {
		Inventory newInventory = new Inventory();
		Apple test1 = new Apple("red");
		test1.pickUpItem(test1);
		test1.putDownItem(test1);
		test1.putInInventory(test1);
		for(Item x : Inventory.getInventory()) {
			System.out.println(x.getDescription());
		}
		
	}

}
