package com.bayviewglen.zork;

public class Exit {
	private Room room;
	private Item requiredItem;

	public Exit(Room room, Item requiredItem) {
		this.room = room;
		this.requiredItem = requiredItem;

	}

	public Room getRoom() {
		return room;
	}

	public Item getRequiredItem() {
		return requiredItem;
	}
}
