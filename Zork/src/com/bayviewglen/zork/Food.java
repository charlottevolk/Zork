package com.bayviewglen.zork;

public class Food extends Item{

	private static Food[] validFoods;

	public Food(String type, String property) {
		super(type, property);
	}

	public void eat() {
		System.out.println("You ate the " + getDescription());
	}

	public static void setValidFoods() {
		validFoods = new Food[10];
		validFoods[0] = new Apple("");
		validFoods[1] = new Apple("red");
		validFoods[2] = new Apple("green");
		validFoods[3] = new Bread("");
		validFoods[4] = new Bread("white");
		validFoods[5] = new Bread("brown");
		validFoods[6] = new Stew("");
		validFoods[7] = new Stew("pinkish");
		validFoods[8] = new Cheese("");
		validFoods[9] = new Cheese("cheddar");
		

	}

	public static Food[] getValidFoods() {
		return validFoods;
	}

	public static boolean isInValidFoods(Item item) {
		for(int i=0; i<validFoods.length; i++) {
			if(item.getDescription().equals(validFoods[i].getDescription())) {
				//item.getType() != null && item.getProperty() != null && 
				return true;
			}
		}
		return false;
	}

}