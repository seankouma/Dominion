
public class Action extends Card {
	int worth, addc, adda, addb;
	Action(String Name, int numCards, int cost, int worth, int vp, int addc, int adda, int addb, String buy, char id, int shuffle) {
			this.name = Name;
			this.numCards = numCards;
			this.cost = cost;
			this.worth = worth;
			this.addc = addc;
			this.adda = adda;
			this.addb = addb;
			this.id = id;
			this.forShuffle = shuffle;
			
		}
	@Override
	public Card clone () {
		Card clone1 = new Action(this.name, this.numCards, this.cost, this.worth, 0, this.addc, this.adda, this.addb, "", this.id, Gameplay.forShuffle);
		Gameplay.forShuffle++;
		return clone1;
	}
}
