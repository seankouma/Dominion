import java.util.ArrayList;

public class CardPrinter {
	public static final int CARD_WIDTH = 17;
	public static final int CARD_HEIGHT = 7;
	
	static void printHorizontalBorder() {
		System.out.println("+" + makeCharString('-', CARD_WIDTH) + "+");
	}
	
	static void printLine(String string) {
		if (stringIsNone(string)){
			return;
		}
		if (string.length() > CARD_WIDTH) {
			ArrayList<String> strings = wrapString(string);
			for (String s: strings) {
				printLineHelper(s);
			}
		}
		else {
			printLineHelper(string);
		}
	}
	
	static private void printLineHelper(String string) {
		int paddingLength = CARD_WIDTH - string.length();
		String padding = makeCharString(' ', paddingLength);
		System.out.println("|" + string + padding + "|");
	}
	
	static void printLine(String value, int i) {
		printLine(value + ": " + Integer.toString(i));
	}
	
	static void printLine(String value, char c) {
		printLine(value + ": " + c);
	}	 
	
	static String makeCharString(char c, int length) {
		StringBuilder stringOfChars = new StringBuilder();
		for (int i = 0; i < length; i++) {
			stringOfChars.append(c);
		}
		return stringOfChars.toString();
	}
	
	static private boolean stringIsNone(String string) {
		if(string.equals("none")) {
			return true;
		}
		return false;
	}
	
	static private ArrayList<String> wrapString(String string){
		ArrayList<String> strings = new ArrayList<String>();
		while(string.length() > CARD_WIDTH ) {
			int position = findPreviousSpace(string, CARD_WIDTH);
			strings.add(string.substring(0, position));
			string = string.substring(position);
			if (string.charAt(0) == ' ') {
				string = string.substring(1);
			}
		}
		if (string.length() > 0) {
			strings.add(string);
		}
		return strings;
	}
	
	static private int findPreviousSpace(String line, int position) {
		while (line.charAt(position) != ' ') {
			position--;
		}
		return position;
	}
}