package com.bayviewglen.zork;

public class Food extends Item{

	private Food[] validFoods;

	public Food(String type, String property) {
		super(type, property);
	}

	public void eat() {
		System.out.println("You ate the " + getDescription());
	}

	public void setValidFoods() {
		validFoods = new Food[10];
		validFoods[0] = new Apple("red");
		validFoods[1] = new Apple("green");
		validFoods[2] = new Bread("white");
		validFoods[3] = new Bread("brown");

	}

	public Food[] getValidFoods() {
		return validFoods;
	}

	public boolean isInValidFoods(Item item) {
		for(int i=0; i<validFoods.length; i++) {
			if(item.getDescription().equals(validFoods[i].getDescription())) {
				return true;
			}
		}
		return false;
	}

}