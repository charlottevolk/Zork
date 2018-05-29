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
		validFoods = new Food[50];
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
		validFoods[10] = new Chocolate("");
		validFoods[11] = new Chocolate("dark");
		validFoods[12] = new Chocolate("white");
		validFoods[13] = new Banana("");
		validFoods[14] = new Banana("yellow");
		validFoods[15] = new Cabbage("");
		validFoods[16] = new Cabbage("purple");
		validFoods[17] = new Pasta("brown");
		validFoods[18] = new Pasta("");
		validFoods[19] = new Fish("");
		validFoods[20] = new Fish("raw");
		validFoods[21] = new Muffin("");
		validFoods[22] = new Muffin("raisin");
		validFoods[23] = new Muffin("carrot");
		validFoods[24] = new Banana("black");
		validFoods[25] = new Berries("");
		validFoods[26] = new Berries("blue");
		validFoods[27] = new Fish("golden");
		validFoods[28] = new Cheese("swiss");
		validFoods[29] = new Cheese("feta");
		validFoods[30] = new Banana("green");
		validFoods[31] = new Cabbage("green");
		validFoods[32] = new Berries("acai");
		validFoods[33] = new Muffin("cinnamon");
		validFoods[34] = new Muffin("pumpkin");
		validFoods[35] = new Muffin("oatmeal");
		validFoods[36] = new Bread("corn");
		validFoods[37] = new Bread("sourdough");
		validFoods[38] = new Bread("rye");
		validFoods[39] = new Bread("ciabatta");
		validFoods[40] = new Bread("garlic");
		
		

	}

	public static Food[] getValidFoods() {
		return validFoods;
	}

	public static boolean isInValidFoods(Item item) {
		for(int i=0; i<validFoods.length; i++) {
			if(item.getDescription().equals(validFoods[i].getDescription())) {
				return true;
			}
		}
		return false;
	}

}