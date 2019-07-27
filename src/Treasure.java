
public class Treasure extends Card {
	int worth;
	Treasure(String Name, int numCards, int cost, int worth, int vp, int addc, int adda, int addb, String equal, char id, int shuffle) {
			this.name = Name;
			this.numCards = numCards;
			this.cost = cost;
			this.worth = worth;
			this.id = id;
			this.forShuffle = shuffle;
			
		}
	@Override
	public Card clone () {
		Gameplay.forShuffle++;
		return (new Treasure(this.name, 1, this.cost, this.worth, 0, 0, 0, 0, "", this.id, Gameplay.forShuffle));
	}
}
