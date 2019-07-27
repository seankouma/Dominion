import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player {
	
	String player;
	static int players = 0;
	int buys = 1;
	int money = 0;
	int actions;
	
	CardList deck = new CardList();
	CardList hand = new CardList();
	CardList discard = new CardList();
	Random r = new Random();
	Scanner keyboard = new Scanner(System.in);
	
	Player(String name) {
		players++;
		player = name;
		// Adds the cards to the deck.
		for (int i = 0; i < 10; i++) {
			Gameplay.forShuffle++;
			if( i < 7) {
				deck.add(Gameplay.tCards.get(0).clone());
				Gameplay.allCards.get('D').numCards--;
			}
			else {
				deck.add(Gameplay.vCards.get(0).clone());
				Gameplay.allCards.get('A').numCards--;
			}
		}

		playerTurn();
		}
//		System.out.println("***********************************************");
//		System.out.println("Discard pile: ");
//		discard.displayCards2();
	
	public void playerTurn() {
		buys = 1;
		actions = 1;
		// Shuffles the deck
		if (deck.size > 0) {
		deck.shuffle();
		}
		
		// Moves the cards from the deck to the hand.
		System.out.println("***********************************************");
//		System.out.println("Your deck, " + name + " is: ");
//		deck.displayCards2();
		System.out.println("***********************************************");
		for (int k = 0; k < 5; k++) {
			if (deck.head == null) {
				discard.shuffle();
				while (discard.head != null) {
					discard.moveFirst(deck);
				}
			}
			deck.moveFirst(hand);
		}
		System.out.println("Your Hand, " + player + ", is: ");
		hand.displayCards2();
		actionPhase();
		buyPhase(countMoney());
		System.out.println("Either you're out of buys, you're out of money, or you chose not to buy a card.");
		buys = 1;
		money = 0;		
		actions = 1;
		// Discards the cards
		while (hand.head != null) {
			hand.moveFirst(discard);
		}
	}
	
	public void buyPhase(int money) {
		while (buys != 0 && money > 0) {
			System.out.println();
			System.out.println("The amount of money you have is: " + money);
			System.out.println("You can buy from the following cards: ");
			Gameplay.allCards.displayCards2();
			System.out.println("You have " + money + " coins. Would you like to buy a card? 1 for Yes, 2 for No. ");
			int c = keyboard.nextInt();
			if (c == 1) {
				System.out.println();
				System.out.println("What card would you like to buy: ");
				String s = keyboard.next();
				char id = s.charAt(0);
				Card buy = Gameplay.allCards.get(id);
				if (buy.cost <= money && buy.numCards > 0) {
					discard.add(buy.clone());
					buy.numCards--;
					this.money -= buy.cost;
					buys--;
					System.out.println("You successfully bought the card.");
				} else {
					System.out.println("Either you do not have enough money to buy that card or there are no more cards of that type.");
				}
			} else {
				System.out.println("You chose not to buy a card.");
				return;
			}
		}
	}
	
	public void actionPhase() {
		hand.displayCards2();
		int actionCards = 0;
		int pos = 0;
		System.out.println();
		System.out.print("The action cards in your hand are: ");
		Card current = hand.head;
		while (current != null) {
			if (current.getClass().getName().equals("Action")) {
				Action buy = (Action) current;
				Gameplay.displayCards1(current);
				actionCards++;
			}
			current = current.next;
		}
		
		while (actionCards > 0 && actions > 0) {
			pos = 0;
			System.out.println("You have " + actionCards + " action cards. Would you like to play any of them? Type 1 for Yes, 2 for No. ");
			int yes = keyboard.nextInt();
			
			if (yes == 1) {
				System.out.println("Which card would you like to play? ");
				String s = keyboard.next();
				char id = s.charAt(0);
				Action buy = (Action) hand.get(id);
				actions += buy.adda;
				buys += buy.addb;
				money += buy.worth;
				if (buy.addc > 0) {
					for (int i = 0; i < buy.addc; i++) {
						if (deck.size > 0) {
							deck.moveFirst(hand);
						} else {
							discard.shuffle();
							while (discard.head != null) {
								discard.moveFirst(deck);
							}
							deck.moveFirst(hand);
						}
					}
				}
				current = hand.head;
					while (!current.name.equals(buy.name)) {
						pos++;
						current = current.next;
					}
				hand.move(discard, pos);
				actionCards--;
				actions--;
				
				System.out.println("The number of actions you have left is " + actions + ", the number of buys you have left is " + buys + ", the amount of money you have left is " + money);
				System.out.println("Your hand is now: ");
				hand.displayCards2();
			} else {
				System.out.println("You chose not to play any of your action cards.");
				return;
			}
			
		}
		
		if (actionCards == 0) {
			System.out.print("You have no action cards");
			if (money > 0 && buys > 0) {
				System.out.println();
				System.out.println("You still have money, would you like to buy anything? 1 for Yes, 2 for No");
				int c = keyboard.nextInt();
				if (c == 1) {
					buyPhase(money);
				} else {
					System.out.println("You chose not to buy a card, it is now the end of your turn.");
					return;
				}
			}
			System.out.println();
			return;
		}
		
	}
	
	public int countMoney() {
		money = 0;
		Card current = hand.head;
		int pos = 0;
		while (current != null) {
			if (current.getClass().getName().equals("Treasure")) {
				Treasure temp = (Treasure) current;
				money += temp.worth;
				hand.move(discard, pos);;
			}
			else {
				pos++;
			}
			current = hand.get(pos);
		}
		return money;
	}

}
