package com.bayviewglen.zork;

public class Apple extends Item{
	
	private String colour;

	public Apple(String enteredColour) {
		super("Apple", enteredColour);
	}
	
	public void setColour(String colour) {
		this.colour = colour;
	}

	@Override
	public void pickUpItem(Item apple) {
		super.pickUpItem(apple);
		System.out.println(getProperty() + " " + getType().toLowerCase());
		
	}

	@Override
	public void putDownItem(Item apple) {
		super.putDownItem(apple);
		System.out.println(getProperty() + " " + getType().toLowerCase());
		
	}

}
