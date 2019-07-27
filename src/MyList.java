public interface MyList {
	public void addToFront(Card c);
	public void addToBack(Card c);
	public void add(int pos, Card c);
	public void add(Card c);
	public void remove(int pos);
	public void removeLast();
	public void removeFirst();
	public Card get(int pos);
	public Card get(char id);
	public void shuffle();
	public void displayCards2();
}
