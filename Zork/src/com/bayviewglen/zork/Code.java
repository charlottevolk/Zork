package com.bayviewglen.zork;

public class Code {
	
	private static final int CODE_LENGTH = 14;
	
	private String[] code = new String[CODE_LENGTH];
	private String[] encryptedCode = new String[CODE_LENGTH];
	
	public Code() {
		code[0] = "Oranges and lemons,";
		code[1] = "Say the bells of St. Clement's.";
		code[2] = "You owe me five farthings,";
		code[3] = "Say the bells of St. Martin's.";
		code[4] = "When will you pay me?";
		code[5] = "Say the bells of Old Bailey.";
		code[6] = "When I grow rich,";
		code[7] = "Say the bells of Shoreditch.";
		code[8] = "When will that be?";
		code[9] = "Say the bells of Stepney.";
		code[10] = "I do not know,";
		code[11] = "Says the great bell of Bow";
		code[12] = "Here comes a candle to light you to bed,";
		code[13] = "Here comes a chopper to chop off your head!";
		
		encryptedCode[0] = "Oranges and lemons,";
		encryptedCode[1] = "???????????????????";
		encryptedCode[2] = "???????????????????";
		encryptedCode[3] = "???????????????????";
		encryptedCode[4] = "???????????????????";
		encryptedCode[5] = "???????????????????";
		encryptedCode[6] = "???????????????????";
		encryptedCode[7] = "???????????????????";
		encryptedCode[8] = "???????????????????";
		encryptedCode[9] = "???????????????????";
		encryptedCode[10] = "???????????????????";
		encryptedCode[11] = "???????????????????";
		encryptedCode[12] = "Here comes a candle to light you to bed,";
		encryptedCode[13] = "Here comes a chopper to chop off your head!";
		
	}
	
	public String getLine(int index) {
		return code[index];
	}
	
	public void unencryptLine(String input) {
		for(int i=0; i<code.length; i++) {
			if(input.equalsIgnoreCase(code[i])) {
				encryptedCode[i] = code[i];
			}
		}
		System.out.println("Here is what you have so far:");
		showEncryptedCode();
		if(isComplete()) {
			System.out.println();
			System.out.println("Congratulations!\nYou have found the full code!\nWhatcha gonna do about that?");
			System.out.println();
		}
	}
	
	public void showEncryptedCode() {
		System.out.println();
		for(int i=0; i<encryptedCode.length; i++) {
			System.out.println("	" + encryptedCode[i]);
		}
	}

	public boolean isComplete() {
		for(int i=0; i<encryptedCode.length; i++) {
			if(!(code[i].equalsIgnoreCase(encryptedCode[i]))) {
				return false;
			}
		}
		return true;
	}
}
