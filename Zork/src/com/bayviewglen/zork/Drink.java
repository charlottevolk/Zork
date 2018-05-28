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
		validDrinks = new Drink[10];
		validDrinks[0] = new Water("");
		validDrinks[1] = new Water("glass");
		validDrinks[2] = new Water("bottle");
		validDrinks[3] = new Coffee("");
		validDrinks[4] = new Coffee("Victory");
		validDrinks[5] = new Liquid("");
		validDrinks[6] = new Liquid("yellow");

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