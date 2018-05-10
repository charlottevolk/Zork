package com.bayviewglen.zork;
/*
 * Author:  Michael Kolling
 * Version: 1.0
 * Date:    July 1999
 * 
 * This class is part of Zork. Zork is a simple, text based adventure game.
 *
 * This parser reads user input and tries to interpret it as a "Zork"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class NewParser 
{
	private ArrayList<String> uselessWords;
	private CommandWords commands;  // holds all valid command words
	private ArrayList<String> input;

	public NewParser() 
	{
		commands = new CommandWords();
		uselessWords = new ArrayList<String>();
		setUselessWords();
	}
	
	private void setUselessWords() {
		uselessWords.add("the");
		uselessWords.add("and");
		uselessWords.add("to");
		uselessWords.add("from");
		uselessWords.add("then");
		uselessWords.add("a");
	}

	public ArrayList<Command> getCommands() 
	{
		String inputLine = "";   // will hold the full input line
		input = new ArrayList<String>();
		ArrayList<Command> inputCommands = new ArrayList<Command>();

		System.out.print("> ");     // print prompt

		BufferedReader reader = 
				new BufferedReader(new InputStreamReader(System.in));
		try {
			inputLine = reader.readLine();
		}
		catch(java.io.IOException exc) {
			System.out.println ("There was an error during reading: "
					+ exc.getMessage());
		}

		StringTokenizer tokenizer = new StringTokenizer(inputLine);
		int i=0;
		while(tokenizer.hasMoreTokens()) {
			input.add(tokenizer.nextToken());
		}
		
		// Remove unneeded words
		input.removeAll(uselessWords);

		// Now check whether this word is known. If so, create a command
		// with it. If not, create a "nil" command (for unknown command).

		for(int i1=0; i1<input.size(); i1++) {
			if(commands.isCommand(input.get(i1))){
				if(i1 == input.size()-1) {
					inputCommands.add(new Command(input.get(i1), null, null));
				}
				else if(i1 == input.size()-2){
					if(commands.isCommand(input.get(i1+1))) {
						inputCommands.add(new Command(input.get(i1), null, null));
					}
					inputCommands.add(new Command(input.get(i1), input.get(i1+1), null));
				}
				else {
					if(commands.isCommand(input.get(i1+1))) {
						inputCommands.add(new Command(input.get(i1), null, null));
					}else if(commands.isCommand(input.get(i1+2))) {
						inputCommands.add(new Command(input.get(i1), input.get(i1+1), null));
					}
					else {
						inputCommands.add(new Command(input.get(i1), input.get(i1+1), input.get(i1+2)));
					}
				}
			}
		}
		for(Command x : inputCommands) {
			System.out.println(x.getCommandWord() + " " + x.getSecondWord() + " " + x.getThirdWord());
			//if(x.getSecondWord().equals(""))
		}
		return inputCommands;
	}

	/**
	 * Print out a list of valid command words.
	 */
	public void showCommands()
	{
		commands.showAll();
	}

	public void printInput() {
		for(String x : input) {
			System.out.println(x);
		}
	}

}