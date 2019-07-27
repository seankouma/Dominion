import java.util.Random;

import javax.xml.soap.Node;

public class CardList implements MyList {
	int size = 0;
	public Card head = null;
	Card tail = head;
	CardList() {
	}
	
	public char getId() {
		return head.id;
	}
	// Adds a card to the front of the list.
	@Override
	public void addToFront(Card c) {
		if (size == 0) {
			head = c;
			size++;
		} else {
			c.next = head;
			head = c;
			size++;
			}
		if (size == 1) {
			tail = head;
		}
	}
	// Adds a card to the back of the list.
	@Override
	public void addToBack(Card c) {
		Card current = head;
		if (size == 0) {
			addToFront(c);
		}
		else if (size == 1) {
			head.next = c;
			tail = c;
			size++;
		}
		else if (size > 1) {
			while (current.next != null) {
				current = current.next;
			}
			current.next = c;
			tail = c;
			size++;
		}
	}
	// Adds a card to the specified position in the list.
	@Override
	public void add(int pos, Card c) {
		if (pos == 0) {
			addToFront(c);
		}
		else if(pos == (size -1)) {
			addToBack(c);
		}
		else if (size > 0) {
			Card current = head;
			for (int j = 0; j < pos -1; j++) {
				current = current.next;
			}
			c.next = current.next;
			current.next = c;
			size++;
		}
		
	}
	// Adds a card to the back of the list.
	@Override
	public void add(Card c) {
		addToBack(c);
	}
	// Removes the card at the specified position.
	@Override
	public void remove(int pos) {
		Card current = head;
		if (size == 0) {
			removeFirst();
		} else if (pos == size -1) {
			removeLast();
		} else {
			for (int k = 0; k < pos -1; k++) {
				current = current.next;
			}
			current.next = current.next.next;
			size--;
		}
		
	}
	// Removes the last card in the list.
	@Override
	public void removeLast() {
		if (size == 1) {
			removeFirst();
		} else {
			Card current = head;
			while (current.next.next != null) {
				current = current.next;
			}
			current.next = null;
			tail = current;
			size--;
		
		}
	}
	// Removes the first card in the list.
	@Override
	public void removeFirst() {
		head = head.next;
		size--;
		
	}
	// Returns the card at a specified location in the list.
	@Override
	public Card get(int pos) {
		Card current = head;
		int test = 0;
		//displayCards2();
//		//System.out.println(head.name);
//		System.out.println(pos);
		if (pos > size) {
			return null;
		}
		if (size == 1 || pos == 0) {
			return head;
		} else if (size > 1) {
			for(int j = 0; j < pos; j++) {
//				System.out.println(head.id);
				//System.out.println(current.next.name + "     " + pos + "     " + test);
				current = current.next;
				test++;
			}
			return current;
		}
		return returnLast();
		
	}
	
	@Override
	public Card get(char id) {
		Card current = get(0);
		if (size == 1) {
			return head;
		} else if (size > 1) {
			while(current != null && current.id != id) {
				current = current.next;
			}
			return current;
		}
		return null;
		
	}
	
	public Card returnLast() {
		Card current = head;
		while (current.next != null) {
			current = current.next;
		}
		return current;
	}
	// Shuffles the cards by choosing a random card and putting it at the start of the list.
	@Override
	public void shuffle() {
		Card temp = head;
		Card previous = head;
		Random rand = new Random();
		Random times = new Random();
		int c = 0;
		
		// Specifies the number of times to choose a random card and put it at the start of the list.
		for (int i = 0; i < 501; i++) {
			temp = head;
			// Grabs the random card
//			for (int j = 0; j < (rand.nextInt(this.size + 1)); j++) {
//				temp = temp.next;
//				c = j;
//			}
			//displayCards2();
			int random = rand.nextInt(this.size);
			temp = get(random);
			previous = head;
//			System.out.println(previous.forShuffle);
//			System.out.println(temp.forShuffle);
			if (!(previous.forShuffle == temp.forShuffle)) {
				while (!(previous.next.forShuffle == temp.forShuffle)) {
					//System.out.println(previous.next.id);
					previous = previous.next;
				}
				previous.next = temp.next;

				if (random >= this.size) {
					tail = previous;
				}
				temp.next = head;
				head = temp;
				//System.out.println(head.id);
			}
		}	
	}
	// Displays an entire list of cards.
	public void displayCards2() {
		for (int i = 0; i < (this.size); i++) {
			CardPrinter.printHorizontalBorder();
			CardPrinter.printLine(get(i).name);
			CardPrinter.printLine("Type: " + get(i).getClass().getName());
			CardPrinter.printLine("numCards", get(i).numCards);
			CardPrinter.printLine("Cost", get(i).cost);
			if (get(i).getClass().getName().equals("Treasure")) {
				Treasure temp = (Treasure) get(i);
				CardPrinter.printLine("Worth", temp.worth);
			}
			else if (get(i).getClass().getName().equals("Action")) {
				Action temp = (Action) get(i);
				CardPrinter.printLine("Worth", temp.worth);
				CardPrinter.printLine("Add Cards" , temp.addc);
				CardPrinter.printLine("Add Actions" , temp.adda);
				CardPrinter.printLine("Add Buy" , temp.addb);
			}
			else if (get(i).getClass().getName().equals("Victory")){
				Victory temp = (Victory) get(i);
				CardPrinter.printLine("Victory Points" , temp.vp);
			}
			CardPrinter.printLine("ID Character" , get(i).id);
			CardPrinter.printHorizontalBorder();
		}
	
}
	// Moves a card from one CardList to another CardList.
	public void moveFirst(CardList a) {
		Card temp = this.get(0);
		this.head = temp.next;
		temp.next = a.head;
		a.head = temp;
		a.size++;
		size--;
	}
	
	public void move(CardList a, int pos) {
		Card temp = this.get(pos);
		if (pos == 0) {
			moveFirst(a);
		}
		else if (pos > 0) {
			Card previous = head;
			while (previous.next != temp) {
				previous = previous.next;
			}
			
			previous.next = temp.next;

			if (pos >= this.size) {
				this.tail = previous;
			}
			temp.next = a.head;
			a.head = temp;
			size--;
			a.size++;
		}
	}
	
	
	
	
}
