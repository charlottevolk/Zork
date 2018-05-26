package com.bayviewglen.zork;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Parser 
{
	private ArrayList<String> uselessWords;
	private CommandWords commands;  // holds all valid command words
	private ArrayList<String> input;
	ArrayList<Command> inputCommands;

	public Parser() 
	{
		commands = new CommandWords();
		uselessWords = new ArrayList<String>();
		setUselessWords();
		Food.setValidFoods();
		Drink.setValidDrinks();
	}

	private void setUselessWords() {
		uselessWords.add("the");
		uselessWords.add("and");
		uselessWords.add("to");
		uselessWords.add("from");
		uselessWords.add("then");
		uselessWords.add("a");
		uselessWords.add("of");
		uselessWords.add("at");
		uselessWords.add("in");
	}


	public ArrayList<Command> getCommands() 
	{
		String inputLine = "";   // will hold the full input line
		input = new ArrayList<String>();
		inputCommands = new ArrayList<Command>();

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
		while(tokenizer.hasMoreTokens()) {
			input.add(tokenizer.nextToken());
		}

		// Remove unneeded words
		input.removeAll(uselessWords);

		// Now check whether this word is known. If so, create a command
		// with it. If not, create a "nil" command (for unknown command).
		
		for(int i=0; i<input.size(); i++) {
			if(commands.isCommand(input.get(i))){
				if(i == input.size()-1) {
					inputCommands.add(new Command(input.get(i), null, null));
				}
				else if(i == input.size()-2){
					if(commands.isCommand(input.get(i+1))) {
						inputCommands.add(new Command(input.get(i), null, null));
					}
					inputCommands.add(new Command(input.get(i), input.get(i+1), null));
				}
				else if(i <= input.size()-3){
					if(commands.isCommand(input.get(i+1))) {
						inputCommands.add(new Command(input.get(i), null, null));
					}else if(commands.isCommand(input.get(i+2))) {
						inputCommands.add(new Command(input.get(i), input.get(i+1), null));
					}
					else if(commands.isCommand(input.get(i))){
						inputCommands.add(new Command(input.get(i), input.get(i+1), input.get(i+2)));
					}
				}
			}
		}

		//removeUnknownCommands();

		return inputCommands;

	}
	/*
	public void removeUnknownCommands() {
		for(int i=0; i<inputCommands.size(); i++) {

			if(inputCommands.getCommandWord()).isUnknown()) {
				inputCommands.remove(i);
				System.out.println("What...");
			}
		}
	}
	 */
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