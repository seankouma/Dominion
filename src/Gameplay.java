/* Sean Kouma
 * Phil Sharp
 * Computer Science II
 * 23 February 2019
 */

import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;

public class Gameplay {
	
	public static CardList vCards = new CardList();
	public static CardList tCards = new CardList();
	public static CardList aCards = new CardList();
	public static CardList allCards = new CardList();
	public static Scanner keyboard = new Scanner(System.in);
	static char id = 'A';
	static int forShuffle = 0;
	static Player one;
	static Player two;
	
	public static void main(String[] args) {
		
		// Reads in the data from cards_1.txt
		try {
			// Reads the text file into a File object.
			File file1 = new File("C:\\Users\\chess\\Downloads\\cards_1.txt");
			// Creates a scanner object so it's easy to print out the data.
			Scanner input = new Scanner(file1);
			storeCards(input);
		} catch (FileNotFoundException e) {
			System.out.println("The file could not be found, sad day.");
		} catch(Exception e) {
			System.out.println("Hmm, that's weird, it threw a weird exception.");
		}
		
//		allCards.displayCards2();
//		System.out.println("***************************************************");
//		System.out.println("***************************************************");
//		System.out.println("***************************************************");
//		System.out.println("Shuffled cards:");
//		allCards.shuffle();
//		allCards.displayCards2();
//		System.out.println("***************************************************");
//		System.out.println("***************************************************");
//		System.out.println("***************************************************");
//		System.out.println();
		InitializeGame();
	}
	// Stores the cards in an CardList.	
	public static void storeCards(Scanner a) {
			String temp = a.nextLine();
			int cur = 0;
			int cur1 = 0;
			int cur2 = 0;
			
			while (temp.equals("victory") || temp.equals("Treasure") || temp.equals("Action")) {
				if (temp.equals("victory")) {
					allCards.add(new Victory(a.nextLine().trim(), a.nextInt(), a.nextInt(), a.nextInt(), a.nextInt(), a.nextInt(), a.nextInt(), a.nextInt(), a.nextLine(), id, forShuffle));
					vCards.add((allCards.returnLast()).clone());
					a.nextLine();
					temp = a.nextLine().trim();
					id++;
					cur++;
					forShuffle++;
				}
				else if (temp.equals("Treasure")) {
					allCards.add(new Treasure(a.nextLine().trim(), a.nextInt(), a.nextInt(), a.nextInt(), a.nextInt(), a.nextInt(), a.nextInt(), a.nextInt(), a.nextLine(), id, forShuffle));
					tCards.add((allCards.returnLast()).clone());
					a.nextLine();
					temp = a.nextLine().trim();
					id++;
					cur1++;
					forShuffle++;
				}
				else if (temp.equals("Action")) {
					allCards.add(new Action(a.nextLine().trim(), a.nextInt(), a.nextInt(), a.nextInt(), a.nextInt(), a.nextInt(), a.nextInt(), a.nextInt(), a.nextLine(), id, forShuffle));
					aCards.add((allCards.returnLast()).clone());
					a.nextLine();
					temp = a.nextLine().trim();
					id++;
					cur2++;
					forShuffle++;
				}
			}
	}
		
		public static void displayCards1(Card current) {
			System.out.println();
				CardPrinter.printHorizontalBorder();
				CardPrinter.printLine(current.name);
				CardPrinter.printLine("Type: " + current.getClass().getName());
				CardPrinter.printLine("Cost", current.cost);
					Action temp = (Action) current;
					CardPrinter.printLine("Worth", temp.worth);
					CardPrinter.printLine("Add Cards" , temp.addc);
					CardPrinter.printLine("Add Actions" , temp.adda);
					CardPrinter.printLine("Add Buy" , temp.addb);
				CardPrinter.printLine("ID Number" , current.id);
				CardPrinter.printHorizontalBorder();
			
		}
		public static void InitializeGame() {
			System.out.println("Player 1, what would you like your player name to be: ");
			String p1 = keyboard.next();
			one = new Player(p1);
			System.out.println("Player 2, what would you like your player name to be: ");
			String p2 = keyboard.next();
			two = new Player(p2);
			while (isGameOver() == false) {
				one.playerTurn();
				if (isGameOver() == false) {
					two.playerTurn();
				}
			}
			}
		
		public static boolean isGameOver() {
			Card current = Gameplay.allCards.head;
			int emptyPiles = 0;
			while (current != null) {
				if (current.numCards == 0) {
					emptyPiles++;
				}
				current = current.next;
			}
			
			return (emptyPiles > 2);
		}
		
		public static Player gameWinner() {
			int p1VP = 0;
			int p2VP = 0;
			// Checking player one's victory points
			Card current = one.discard.head;
			while (current != null) {
				one.discard.moveFirst(one.deck);
				current = current.next;
			}
			current = one.deck.head;
			while (current != null) {
				if (current.getClass().getName().equals("Victory")){
					Victory temp  = (Victory) current;
					p1VP += temp.vp;
				}
				current = current.next;
			}
			// Checking Player two's victory points
			current = two.discard.head;
			while (current != null) {
				two.discard.moveFirst(one.deck);
				current = current.next;
			}
			current = two.deck.head;
			while (current != null) {
				if (current.getClass().getName().equals("Victory")){
					Victory temp  = (Victory) current;
					p2VP += temp.vp;
				}
				current = current.next;
			}
			
			if (p1VP > p2VP) {
				return one;
			}
			if (p1VP < p2VP) {
				return two;
			}
			else {
				return null;
			}
			
		}

}
