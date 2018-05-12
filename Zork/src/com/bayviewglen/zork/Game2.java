package com.bayviewglen.zork;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Class Game - the main class of the "Zork" game.
 *
 * Author: Michael Kolling Version: 1.1 Date: March 2000
 * 
 * This class is the main class of the "Zork" application. Zork is a very
 * simple, text based adventure game. Users can walk around some scenery. That's
 * all. It should really be extended to make it more interesting!
 * 
 * To play this game, create an instance of this class and call the "play"
 * routine.
 * 
 * This main class creates and initialises all the others: it creates all rooms,
 * creates the parser and starts the game. It also evaluates the commands that
 * the parser returns.
 */

class Game2 {
	private NewParser newparser;
	private Room currentRoom;
	private Inventory inventory;
	private Inventory roomInventory;
	
	// This is a MASTER object that contains all of the rooms and is easily
	// accessible.
	// The key will be the name of the room -> no spaces (Use all caps and
	// underscore -> Great Room would have a key of GREAT_ROOM
	// In a hashmap keys are case sensitive.
	// masterRoomMap.get("GREAT_ROOM") will return the Room Object that is the Great
	// Room (assuming you have one).
	private HashMap<String, Room> masterRoomMap;

	private void initRooms(String fileName) throws Exception {
		masterRoomMap = new HashMap<String, Room>();
		Scanner roomScanner;
		try {
			HashMap<String, HashMap<String, String>> exits = new HashMap<String, HashMap<String, String>>();
			roomScanner = new Scanner(new File(fileName));
			while (roomScanner.hasNext()) {
				Room room = new Room();
				// Read the Name
				String roomName = roomScanner.nextLine();
				room.setRoomName(roomName.split(":")[1].trim());
				// Read the Description
				String roomDescription = roomScanner.nextLine();
				room.setDescription(roomDescription.split(":")[1].replaceAll("<br>", "\n").trim());
				// Read the Exits
				String roomExits = roomScanner.nextLine();
				// An array of strings in the format E-RoomName
				String[] rooms = roomExits.split(":")[1].split(",");
				HashMap<String, String> temp = new HashMap<String, String>();
				for (String s : rooms) {
					temp.put(s.split("-")[0].trim(), s.split("-")[1]);
				}

				exits.put(roomName.substring(10).trim().toUpperCase().replaceAll(" ", "_"), temp);

				// Sets items in room (Item type-Item property)
                String[] roomContents = roomScanner.nextLine().split(":")[1].split(",");
                roomInventory = new Inventory();
                for(int i=0; i<roomContents.length; i++) {
                    if(roomContents[i].equals("None-None")) {
                        i++;
                    }else{
                        roomInventory.addItem(new Item(roomContents[i].split("-")[0].trim(), roomContents[i].split("-")[1].trim()));
                    }
                }
                room.setRoomInventory(roomInventory);
				
				// This puts the room we created (Without the exits in the masterMap)
				masterRoomMap.put(roomName.toUpperCase().substring(10).trim().replaceAll(" ", "_"), room);

				// Now we better set the exits.
			}

			for (String key : masterRoomMap.keySet()) {
				Room roomTemp = masterRoomMap.get(key);
				HashMap<String, String> tempExits = exits.get(key);
				for (String s : tempExits.keySet()) {
					// s = direction
					// value is the room.

					String roomName2 = tempExits.get(s.trim());
					Room exitRoom = masterRoomMap.get(roomName2.toUpperCase().replaceAll(" ", "_"));
					roomTemp.setExit(s.trim().charAt(0), exitRoom);

				}

			}

			roomScanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the game and initialise its internal map.
	 */
	public Game2() {
		try {
			initRooms("data/Rooms.dat");
			currentRoom = masterRoomMap.get("PRISON_CELL");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		newparser = new NewParser();
	}

	/**
	 * Main play routine. Loops until end of play.
	 */
	public void play() {
		printWelcome();

		// Enter the main command loop. Here we repeatedly read commands and
		// execute them until the game is over.

		boolean finished = false;
		while (!finished) {
			ArrayList<Command> commandList = newparser.getCommands();
			for(int i=0; i<commandList.size(); i++) {
				finished = processCommand(commandList.get(i));
			}
			
		}
		System.out.println("Thank you for playing.  Good bye.");
	}

	/**
	 * Print out the opening message for the player.
	 */
	private void printWelcome() {
        Scanner scanner= new Scanner(System.in);
        System.out.println();
        System.out.println("...Please press enter after each line...");
        scanner.nextLine();
        System.out.println("...");
        scanner.nextLine();
        System.out.println("darkness...");
        scanner.nextLine();
        System.out.print("Your eyes shoot open. You inhale sharply as");
        scanner.nextLine();
        System.out.print("your air-deprived lungs begs for oxygen.");
        scanner.nextLine();
        System.out.print("You find yourself to be lying down on a bed,");
        scanner.nextLine();
        System.out.print("a pillow under your head and a blanket over your body.");
        scanner.nextLine();
        System.out.print("You push head up (creating a crinkling sound),");
        scanner.nextLine();
        System.out.print("move the blanket to the side,");
        scanner.nextLine();
        System.out.print("and get off the bed to stand and look around.");
        scanner.nextLine();
        System.out.println();
        System.out.println(currentRoom.longDescription());
    }


	/**
	 * Given a command, process (that is: execute) the command. If this command ends
	 * the game, true is returned, otherwise false is returned.
	 */
	private boolean processCommand(Command command) {
		if (command.isUnknown()) {
			System.out.println("I don't know what you mean...");
			return false;
		}

		String commandWord = command.getCommandWord();
		if (commandWord.equals("help"))
			printHelp();
		else if (commandWord.equals("walk"))
			goRoom(command);
		else if (commandWord.equals("quit")) {
			if (command.hasSecondWord())
				System.out.println("Quit what?");
			else
				return true; // signal that we want to quit
		//} else if (commandWord.equals("eat")) {
		//	System.out.println("Do you really think you should be eating at a time like this?");
		} else if (commandWord.equals("run")) {
			if (command.getSecondWord() != null && command.getSecondWord().equals("away")) {
				System.out.println(
						"You are caught by the Thought Police.\nYou attempt to fight back,\nthrowing you strongest punch.\nIt was ineffective, but we acknowledge your efforts.\nThey shoot a tranqilizer dart into you neck.\nYou drop to the ground with a thud and all you see is");
			} else {
				System.out.println("Why run when you could walk?");
			}
		}
		return false;
	}

	// implementations of user commands:

	/**
	 * Print out some help information. Here we print some stupid, cryptic message
	 * and a list of the command words.
	 */
	private void printHelp() {
        System.out.println("You have been captured.\nYou need to escape\nthe Ministry of Love.");
 
 
        System.out.println("Your command words are:");
        newparser.showCommands();
    }

	/**
	 * Try to go to one direction. If there is an exit, enter the new room,
	 * otherwise print an error message.
	 */
	private void goRoom(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know where to go...
			System.out.println("Walk where?");
			return;
		}

		String direction = command.getSecondWord();

		if (command.getSecondWord().equals("up")) {
			// if(command.getThirdWord().equals("stairs"){
			// Room nextRoom = currentRoom.nextRoom(direction);
			// }else{
			// System.out.println("You can't walk up into thin air!");
		}

		// Try to leave current room.
		Room nextRoom = currentRoom.nextRoom(direction);

		if (nextRoom == null)
			System.out.println("There is no door!");
		else {
			currentRoom = nextRoom;
			System.out.println(currentRoom.longDescription());
		}
	}
	
	public Inventory getCurrentRoomInventory() {
		return currentRoom.getRoomContents();
	}

}