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

class Game {
	private Parser parser;
	private Room currentRoom;
	private Inventory inventory = new Inventory();
	private Inventory roomInventory;
	private Code code = new Code();
	private Stats stats = new Stats();
	private MusicManager music = new MusicManager();

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
				String[] roomContents = roomScanner.nextLine().toLowerCase().split(":")[1].split(",");
				roomInventory = new Inventory();
				for(int i=0; i<roomContents.length; i++) {
					if(roomContents[i].equals("None-None")) {
						i++;
					}else{
						roomInventory.addItem(new Item(roomContents[i].toLowerCase().split("-")[0].trim(), roomContents[i].toLowerCase().split("-")[1].trim()));
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
	public Game() {
		try {
			initRooms("data/Rooms.dat");
			currentRoom = masterRoomMap.get("PRISON_CELL");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		parser = new Parser();
	}

	/**
	 * Main play routine. Loops until end of play.
	 
	 */
	public void play(){
		music.playClip("dark_world.wav");
		printWelcome();

		// Enter the main command loop. Here we repeatedly read commands and
		// execute them until the game is over.

		boolean finished = false;
		while (!finished) {
			ArrayList<Command> commandList = parser.getCommands();
			System.out.println();
			boolean noHunger = !(stats.getHunger().reduce());
			boolean noThirst = !(stats.getThirst().reduce());
			if(noHunger || noThirst) {
				if(noHunger) {
					dieOf("hunger");
					System.out.println();
					finished = true;
				}else if(noThirst) {
					dieOf("thirst");
					System.out.println();
					finished = true;
				}
			}else {
				for(int i=0; i<commandList.size(); i++) {
					finished = processCommand(commandList.get(i));
				}
			}

		}
		System.out.println("Thank you for playing. Good bye.");
	}

	/**
	 * Print out the opening message for the player.
	 */
	private void printWelcome() {
		Scanner scanner= new Scanner(System.in);
		//System.out.println();
		System.out.println("...Please press enter after each line...");
		//scanner.nextLine();
		System.out.println("...");
		//scanner.nextLine();
		System.out.println("darkness...");
		//scanner.nextLine();
		System.out.print("Your eyes shoot open. You inhale sharply as");
		//scanner.nextLine();
		System.out.print("your air-deprived lungs begs for oxygen.");
		//scanner.nextLine();
		System.out.print("You find yourself to be lying down on a bed,");
		//scanner.nextLine();
		System.out.print("a pillow under your head and a blanket over your body.");
		//scanner.nextLine();
		System.out.print("You push head up (creating a crinkling sound),");
		//scanner.nextLine();
		System.out.print("move the blanket to the side,");
		//scanner.nextLine();
		System.out.print("and get off the bed to stand and look around.");
		//scanner.nextLine();
		System.out.println();
		System.out.println(currentRoom.longDescription());
		System.out.println();
		stats.getHunger().printHunger();
		System.out.println();
		stats.getThirst().printThirst();
		System.out.println();
	}


	/**
	 * Given a command, process (that is: execute) the command. If this command ends
	 * the game, true is returned, otherwise false is returned.
	 */
	private boolean processCommand(Command command) {
		String commandOut = command.getCommandWord() + " " + command.getSecondWord();
		if (command.getThirdWord() != null) {
			commandOut += " " + command.getThirdWord();
		}
		if (command.isUnknown()) {
			System.out.println("I don't know what you mean...");
			return false;
		}

		String commandWord = command.getCommandWord();
		if (commandWord.equals("help"))
			printHelp();
		else if(commandWord.equals("climb")) {
			System.out.println("Who are you, spiderman??");
		}else if(commandWord.equals("dance")) {
			System.out.println("The thought police watches your slick moves and challenges you to a dance battle.");
			System.out.println("However, in the midst of your moon walk, they taze you!");
			gameOver();
			return true;
		}else if(commandWord.equals("quit")) {
			System.out.println("Tsk tsk tsk.");
			System.out.println("Quiting is never the answer.");
		}else if(commandWord.equals("walk") && command.getSecondWord() != null && command.getSecondWord().equals("south") && currentRoom.getRoomName().equalsIgnoreCase("Prison Cell")) {
			System.out.println("Cannot exit without pass code. Please enter pass code.");
			System.out.println("-------------");
			System.out.println("| 1 | 2 | 3 |");
			System.out.println("-------------");
			System.out.println("| 4 | 5 | 6 |");
			System.out.println("-------------");
			System.out.println("| 7 | 8 | 9 |");
			System.out.println("-------------");
			System.out.println("    | 0 |    ");
			System.out.println("    -----    ");
		}else if(commandWord.equals("4218") && currentRoom.getRoomName().equalsIgnoreCase("Prison Cell")){
			goRoom(new Command("walk", "south", null));
		}else if(commandWord.equals("walk") && command.getSecondWord() != null && command.getSecondWord().equals("south") && currentRoom.getRoomName().equalsIgnoreCase("Room 101")) {
			System.out.println("Oh no! The door is locked and you are stuck here!\nThe only way to escape is to correctly answer my riddle on the first try.\nIf you do not succeed, you will die a gruesome and painful death.\nDo you accept this challenge?");
		}else if(commandWord.equals("yes") && currentRoom.getRoomName().equalsIgnoreCase("Room 101")) {
			System.out.println("This is the riddle:");
			System.out.println();
			System.out.println("It cannot be seen, cannot be felt");
			System.out.println("Cannot be heard, cannot be smelt.");
			System.out.println("It lies behind stars and under hills,");
			System.out.println("And empty holes it fills.");
			System.out.println("It comes first and follows after,");
			System.out.println("Ends life, kills laughter.");
			System.out.println();
			System.out.println("Enter your answer.");
			System.out.println("Your answer must have Answer: \nwith a space after the colon in order to avoid certain death!");
		}else if(commandWord.equals("Answer:")) {
			if(command.getSecondWord().equalsIgnoreCase("dark")) {
				System.out.println("Congratulations! You escaped!");
				System.out.println();
				goRoom(new Command("walk", "south", null));
			}else {
				gameOver();
				return true;
			}


		}else if(commandWord.equals("no") && currentRoom.getRoomName().equalsIgnoreCase("Room 101")) {
			gameOver();
			return true;
		}else if (commandWord.equals("walk")) {
			if(command.getSecondWord() != null)
				goRoom(command);
			else
				System.out.println("Walk where?!");
		}else if (commandWord.equals("quit")) {
			if (command.hasSecondWord())
				System.out.println("Quit what?");
			else
				return true; // signal that we want to quit
		}else if (commandWord.equals("run")) {
			if (command.getSecondWord() != null && command.getSecondWord().equals("away")) {
				System.out.println(
						"You are caught by the Thought Police.\nYou attempt to fight back,\nthrowing your strongest punch.\nIt was ineffective, but we acknowledge your efforts.\nThey shoot a tranqilizer dart into your neck.\nYou drop to the ground with a thud.");
				gameOver();
				return true;
			} else {
				System.out.println("Why run when you could walk?");
			}

			// Code for all permutations of a Command with commandWord "take"
		}else if(command.getCommandWord().equals("take") && command.getSecondWord() != null && command.getSecondWord().equals("dark") && command.getThirdWord() != null && command.getThirdWord().equals("chocolate") && currentRoom.getRoomName().equalsIgnoreCase("Secret Room")) {
			Item item = new Item(command.getThirdWord(), command.getSecondWord());
			item.pickUpItem();
			currentRoom.getRoomInventory().removeItem(item);		// DOESN'T WORK. FIX ASAP
			inventory.addItem(item);
			System.out.println("OMG!! You found a trapdoor! There is a small display screen with the words \n\"Insert code\" written on it.\nBelow is a small slot to accomodate a piece of paper.\nThis could be your chance to escape!\nWhat do you want to do next?");
		}else if(command.getCommandWord().equals("insert") && (command.getSecondWord()== null)) {
			System.out.println("What are you inserting?");
		}else if(command.getCommandWord().equals("insert") && command.getSecondWord().equals("code") && currentRoom.getRoomName().equalsIgnoreCase("Secret Room")) {
			if(code.isComplete()) {
				winGame();
			}else {
				System.out.println("Unfortunately, you do not have the full code.\nSo find the rest of it first and then try again :)");
			}
		}else if(command.getCommandWord().equals("take")){
			if(command.getSecondWord() == null && command.getThirdWord() == null) {
				System.out.println("Take what?!");
			}else if(ItemsInGame.isInGame(command.getThirdWord())) {
				Item item = new Item(command.getThirdWord(), command.getSecondWord());
				if(isInRoom(item)) {
					if(item.canPickUp()) {
						item.pickUpItem();
						currentRoom.getRoomInventory().removeItem(item);		// DOESN'T WORK. FIX ASAP
						inventory.addItem(item);

					}else {
						System.out.println("You can't pick that up!");
					}
				}else {
					System.out.println("There is nothing like that in the room...");
				}
			}else if(ItemsInGame.isInGame(command.getSecondWord())) {
				Item item = new Item(command.getSecondWord(), "");
				if(isInRoom(item)) {
					if(item.canPickUp()) {
						item.pickUpItem();
						roomInventory.removeItem(item);
						inventory.addItem(item);
					}else {
						System.out.println("You can't pick that up!");
					}
				}else {
					System.out.println("Either there is nothing like that in the room, or you weren't specific enough.\nI'm not a mind-reader, you know.");
				}
			}else {
				System.out.println("There is nothing like that in the game...");
			}


			// Code for all permuations of "drop"
		}else if(command.getCommandWord().equals("drop")){
			if(command.getSecondWord() == null && command.getThirdWord() == null) {
				System.out.println("Drop what?!");
			}else if(ItemsInGame.isInGame(command.getThirdWord())) {
				Item item = new Item(command.getThirdWord(), command.getSecondWord());
				if(isInInventory(item)) {
					if(item.canPutDown()) {
						item.putDownItem();
						currentRoom.getRoomInventory().addItem(item);
						inventory.removeItem(item);

					}else {
						System.out.println("You can't put that down!");
					}
				}else {
					System.out.println("There is nothing like that in your inventory...");
				}
			}else if(ItemsInGame.isInGame(command.getSecondWord())) {
				Item item = new Item(command.getSecondWord(), "");
				if(isInInventory(item)) {
					if(item.canPutDown()) {
						item.putDownItem();
						roomInventory.addItem(item);
						inventory.removeItem(item);
					}else {
						System.out.println("You can't put that down!");
					}
				}else {
					System.out.println("Either there is nothing like that in your inventory, or you weren't specific enough.\nI'm not a mind-reader, you know.");
				}
			}else {
				System.out.println("There is nothing like that in the game...");
			}



			// Code for all permutations of a Command with commandWord "eat"
		}else if(command.getCommandWord().equals("eat")) {
			if(command.getSecondWord() == null && command.getThirdWord() == null) {
				System.out.println("Eat what?!");
			}else if(ItemsInGame.isInGame(command.getThirdWord())) {
				Food food = new Food(command.getThirdWord(), command.getSecondWord());
				if(isInInventory(food)) {
					if(food.canEat() && Food.isInValidFoods(food)) {
						food.eat();
						inventory.removeItem(food);
						stats.getHunger().increase();
					}else {
						System.out.println("You really thought you could eat that?!");
					}
				}else {
					System.out.println("Either there is nothing like that in your inventory, (i.e. take it first!!!)\nor you weren't specific enough.\nI'm not a mind-reader, you know.");
				}
			}else if(ItemsInGame.isInGame(command.getSecondWord())) {
				Food food = new Food(command.getSecondWord(), "");
				if(isInInventory(food)) {
					if(food.canEat() && Food.isInValidFoods(food)) {
						food.eat();
						inventory.removeItem(food);
						stats.getHunger().increase();
					}else {
						System.out.println("You really thought you could eat that?!");
					}
				}else {
					System.out.println("Either there is nothing like that in your inventory, (i.e. take it first!!!)\nor you weren't specific enough.\nI'm not a mind-reader, you know.");
				}
			}else {
				System.out.println("There is nothing like that in the game...");
			}


			// Code for all permutations of a Command with commandWord "drink"
		}else if(command.getCommandWord().equals("drink")) {
			if(command.getSecondWord() == null && command.getThirdWord() == null) {
				System.out.println("Drink what?!");
			}else if(ItemsInGame.isInGame(command.getThirdWord())) {
				Drink drink = new Drink(command.getThirdWord(), command.getSecondWord());
				if(isInInventory(drink)) {
					if(drink.canDrink() && Drink.isInValidDrinks(drink)) {
						drink.drink();
						inventory.removeItem(drink);
						stats.getThirst().increase();
					}else {
						System.out.println("You really thought you could drink that?!");
					}
				}else {
					System.out.println("Either there is nothing like that in your inventory, (i.e. take it first!!!)\nor you weren't specific enough.\nI'm not a mind-reader, you know.");
				}
			}else if(ItemsInGame.isInGame(command.getSecondWord())) {
				Drink drink = new Drink(command.getSecondWord(), "");
				if(isInInventory(drink)) {
					if(drink.canDrink() && Drink.isInValidDrinks(drink)) {
						drink.drink();
						inventory.removeItem(drink);
						stats.getThirst().increase();
					}else {
						System.out.println("You really thought you could drink that?!");
					}
				}else {
					System.out.println("Either there is nothing like that in your inventory, (i.e. take it first!!!)\nor you weren't specific enough.\nI'm not a mind-reader, you know.");
				}
			}else {
				System.out.println("There is nothing like that in the game...");
			}

			//Code for all permutations of "look"
		}else if(command.getCommandWord().equals("look")) {
			if(command.getSecondWord() == null) {
				System.out.println("What you lookin' at?");
			}else if(currentRoom.getRoomName().equalsIgnoreCase("Room 101") && command.getSecondWord().equals("around")) {
				room101();
			}else if(command.getSecondWord().equals("around")) {
				System.out.println(currentRoom.longDescription());
			}else if(currentRoom.getRoomName().equalsIgnoreCase("Prison Cell")) {
				if(command.getSecondWord().equalsIgnoreCase("mirror")) {
					System.out.println("Your face has lost all colour it once had, a dark grey colour \nhighlights your cheek bones and eyebags, and you have lost \nenough weight that your bones can be easily seen through your \ntransparent looking skin. Out of the corner of your eye, it can \nbe seen that the mirror has  inverted the scribbles on the walls,\nforming a legible message: \r\n" + 
							"	\"An unknown path you'll find below,  \r\n" + 
							"	one that none has ever known.\r\n" + 
							"	Complete the song and seal your fate,\r\n" + 
							"	Your one and only key to escape.\r\n" + 
							"	for to won ate\"");
				}else if(command.getSecondWord().equalsIgnoreCase("pillow") || command.getThirdWord().equalsIgnoreCase("pillow")) {
					System.out.println("You find a wrinkled note. Written on it is one line,\nrandom symbols, and then two more lines.\nIt looks like this:");
					code.showEncryptedCode();
				}
			}
			
			
			//Code for all permutations of "view"
		}else if(command.getCommandWord().equalsIgnoreCase("view")) {
			if(command.getSecondWord() == null) {
				System.out.println("And what would you like to view today?");
			}else if(command.getSecondWord().equalsIgnoreCase("stats")) {
				stats.getHunger().printHunger();
				System.out.println();
				stats.getThirst().printThirst();
				System.out.println();
			}else if(command.getSecondWord().equalsIgnoreCase("hunger")) {
				stats.getHunger().printHunger();
				System.out.println();
			}else if(command.getSecondWord().equalsIgnoreCase("thirst")) {
				stats.getThirst().printThirst();
				System.out.println();
			}
			
			// Code for all permutations of "open" (controls finding lines of the code inside books/boxes)
		}else if(command.getCommandWord().equals("open")){
			if(command.getSecondWord() == null && command.getThirdWord() == null) {
				System.out.println("Open what?!");
			}else if(ItemsInGame.isInGame(command.getThirdWord())) {
				InanimateItem item = new InanimateItem(command.getThirdWord(), command.getSecondWord());
				if(isInInventory(item)) {
					if(item.canOpen()) {
						open(item);
					}else {
						System.out.println("You can't open that!");
					}
				}else {
					System.out.println("Either there is nothing like that in your inventory, (i.e. take it first!!!)\nor you weren't specific enough.\nI'm not a mind-reader, you know.");
				}
			}else if(ItemsInGame.isInGame(command.getSecondWord())) {
				InanimateItem item = new InanimateItem(command.getSecondWord(), "");
				if(isInInventory(item)) {
					if(item.canOpen()) {
						open(item);
					}else {
						System.out.println("You can't open that!");
					}
				}else {
					System.out.println("Either there is nothing like that in your inventory, (i.e. take it first!!!)\nor you weren't specific enough.\nI'm not a mind-reader, you know.");
				}
			}else {
				System.out.println("There is nothing like that in the game...");
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
		parser.showCommands();
	}

	/**
	 * Try to go to one direction. If there is an exit, enter the new room,
	 * otherwise print an error message.
	 */
	private void goRoom(Command command) {
		music.playClip("dark_world.wav");
		
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know where to go...
			System.out.println("Walk where?");
			return;
		}

		String direction = command.getSecondWord();

		// Try to leave current room.
		Room nextRoom = currentRoom.nextRoom(direction);

		if (nextRoom == null)
			System.out.println("There is no door!");
		else if(nextRoom.getRoomName().equalsIgnoreCase("Room 101")) {
			currentRoom = nextRoom;
			music.playClip("Strange_atmosphere.wav");
			System.out.println(currentRoom.shortDescription());
		}else if(nextRoom.getRoomName().equalsIgnoreCase("Shrine")) {
			currentRoom = nextRoom;
			music.playClip("nightmare_returns.wav");
			System.out.println(currentRoom.shortDescription());
		}else if(nextRoom.getRoomName().equalsIgnoreCase("Secret Room")) {
			currentRoom = nextRoom;
			music.playClip("SecretRoom.wav");
			System.out.println(currentRoom.shortDescription());
		}
		else {
			currentRoom = nextRoom;
			System.out.println(currentRoom.longDescription());
		}
	}

	public Inventory getCurrentRoomInventory() {
		return currentRoom.getRoomInventory();
	}

	public void putInInventory(Item item) {
		getCurrentRoomInventory().addItem(item);
	}

	public boolean isInRoom(Item item) {
		for(int i=0; i<currentRoom.getRoomInventory().howManyItems(); i++) {
			if(currentRoom.getRoomInventory().getItem(i).getDescription().equalsIgnoreCase(item.getDescription())) {
				return true;
			}
		}
		return false;
	}

	public boolean isInInventory(Item item) {
		for(int i=0; i<inventory.howManyItems(); i++) {
			if(item.getType().equalsIgnoreCase(inventory.getItem(i).getType()) && item.getProperty().equalsIgnoreCase(inventory.getItem(i).getProperty())) {
				return true;
			}
		}
		return false;
	}

	public void open(InanimateItem object) {
		object.open();
		if(object.getType().equals("book")) {
			if(object.getProperty().equals("old")) {
				System.out.println("You found a line of the code!!");
				code.unencryptLine(code.getLine(1));
			}
		}else if(object.getType().equals("container")) {
			if(object.getProperty().equals("plastic")) {
				System.out.println("You found a line of the code!!");
				code.unencryptLine(code.getLine(2));
			}
		}
		else {
			System.out.println("There is nothing inside!");
		}

	}

	private void dieOf(String type) {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("*****************************************");
		System.out.println("Wow. You died of " + type + ". What an ignominious death.");
	}

	private void gameOver() {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("*****************************************");
		System.out.println("A slow chuckling is heard.\n\nAn iron voice says \"You have made a grave error!\"\nand you are suddenly paralyzed, with no way to move or speak.\n\nThe only thing you can move are your eyes.\n\nYou notice smoke drifting up past your face,\nand you realize that the room is on fire.\n\nAs you look back up in panic, you see a large,\nsharp-looking metal blade right in front of your face,\nand slowly inching forward until it is pressing into your neck.\n\nThe iron voice says gleefully \"And by the way, while we are on the subject,\n\n'Here comes a candle to light you to bed,\nHere comes a chopper to chop off your head!'\"\n\nYou shut your eyes, and wait for the inevitable death that awaits you.");
		System.out.println();
		System.out.println("You have lost. Too bad so sad.");
	}
	
	private void room101(){
		music.playClip("Strange_atmosphere.wav");
		
		System.out.println("*Please press enter after each line*");
		//scanner.nextLine();
		System.out.println();
		System.out.println("There are concrete walls on your left and right.");
		//scanner.nextLine();
		System.out.println("\"I had good faith in you,\" says a voice in the shadows.");
		//scanner.nextLine();
		System.out.println("The dark figure steps slowly, coming closer. It was O'Brien.");
		//scanner.nextLine();
		System.out.println("You struggle to speak, gibberish slipping through your numb lips. He holds up a used syringe.");
		//scanner.nextLine();
		System.out.println("\"Lignocaine, but a much larger dosage. You won't be able to move, nor speak for hours.\"");
		//scanner.nextLine();
		System.out.println("He take a seat on the edge of the bed, exhaling a deep sigh.");
		//scanner.nextLine();
		System.out.println("\"You showed promising results, great loyalty to our nation, to Big Brother.");
		//scanner.nextLine();
		System.out.println("Even during the tests, you've always outranked the others. We were considering to release you.");
		//scanner.nextLine();
		System.out.println("But it seems like it was all a trick. You are never going to escape now.");
		//scanner.nextLine();
		System.out.println("Become familiar to this room, to this new lifestyle, because you are now the enemy.\"");
		//scanner.nextLine();
		System.out.println("He stands up, dusting off the wrinkles in his trousers, and walks past your head.");
		//scanner.nextLine();
		System.out.println("You hear the sound of a keypad as he types something in. A click echoes as he turns a knob and exits.");
		//scanner.nextLine();
		System.out.println("The door slams shut, a resounding click confirming it is locked.");
		//scanner.nextLine();
		System.out.println("Your legs and arms begin to tingle as warmth spreads though your body. You are now able to move.");
		//scanner.nextLine();
		System.out.println("You stand up slowly and look around the room. It's empty except for the bed and the door (south).");
		//scanner.nextLine();
		System.out.println();
		
	}

	private void winGame() {
		System.out.println("You won!");
	}

}