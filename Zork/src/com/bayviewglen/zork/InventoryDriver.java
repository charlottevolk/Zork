package com.bayviewglen.zork;

public class InventoryDriver {
	public static void main(String[] args) {
		Inventory newInventory = new Inventory();
		Apple test1 = new Apple("red");
		Bread test2 = new Bread("brown");
		Drink test3 = new Water("glass of");
		test1.pickUpItem();
		test1.putDownItem();
		test1.putInInventory(test1);
		test1.eat();
		test2.pickUpItem();
		test2.putDownItem();
		test2.putInInventory(test2);
		test2.eat();
		test3.drink();
		test3.putInInventory(test3);
		
		for(Item x : Inventory.getInventory()) {
			System.out.println(x.getDescription());
		}
		
	}

}