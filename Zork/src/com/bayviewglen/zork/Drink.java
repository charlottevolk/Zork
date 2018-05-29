package com.bayviewglen.zork;

public class Drink extends Item{
	
	private static Drink[] validDrinks;

	public Drink(String type, String property) {
		super(type, property);
	}
	
	public void drink() {
		if(getType().equalsIgnoreCase("water") && getProperty().equalsIgnoreCase("glass")){
			System.out.println("You drank the " + getProperty() + " of " + getType());
		}else{
			System.out.println("You drank the " + getDescription());
		}
	}
	
	public static void setValidDrinks() {
		validDrinks = new Drink[20];
		validDrinks[0] = new Water("");
		validDrinks[1] = new Water("glass");
		validDrinks[2] = new Water("bottle");
		validDrinks[3] = new Coffee("");
		validDrinks[4] = new Coffee("Victory");
		validDrinks[5] = new Liquid("");
		validDrinks[6] = new Liquid("yellow");
		validDrinks[7] = new Milk("");
		validDrinks[8] = new Milk("white");
		validDrinks[9] = new Juice("");
		validDrinks[10] = new Juice("orange");
		validDrinks[11] = new Tea("");
		validDrinks[12] = new Tea("hot");
		

	}

	public static Drink[] getValidDrinks() {
		return validDrinks;
	}

	public static boolean isInValidDrinks(Item item) {
		for(int i=0; i<validDrinks.length; i++) {
			if(item.getDescription().equalsIgnoreCase(validDrinks[i].getDescription())) { 
				return true;
			}
		}
		return false;
	}

}