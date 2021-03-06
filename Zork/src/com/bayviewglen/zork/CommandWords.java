package com.bayviewglen.zork;
/*
 * Author:  Michael Kolling.
 * Version: 1.0
 * Date:    July 1999
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * This class is part of the "Zork" game.
 */

class CommandWords {
	// a constant array that holds all valid command words
	private static final String validCommands[] = { "quit", "help", "walk", "run", "take", "drop", "climb", "dance", "eat", "drink", "open", "look", "view", "insert", "sing", "flip", "shake", "roll", "gallop", "yes", "no", "Answer:", "4218"};

	/**
	 * Constructor - initialise the command words.
	 */
	public CommandWords() {
		// nothing to do at the moment...
	}

	/**
	 * Check whether a given String is a valid command word. Return true if it is,
	 * false if it isn't.
	 **/
	public boolean isCommand(String aString) {
		for (int i = 0; i < validCommands.length; i++) {
			if (validCommands[i].equalsIgnoreCase(aString))
				return true;
		}
		// if we get here, the string was not found in the commands
		return false;
	}

	/*
	 * Print all valid commands to System.out.
	 */
	public void showAll() { 	// does not show "4218", "yes" or "no" as commands
		for (int i = 0; i < validCommands.length-4; i++) {
			System.out.print(validCommands[i] + "  ");
		}
		System.out.println();
	}
}