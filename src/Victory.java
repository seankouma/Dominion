
public class Victory extends Card {
	int vp;
	Victory(String Name, int numCards, int cost, int worth, int vp, int addc, int adda, int addb, String buy, char id, int shuffle) {
			this.name = Name;
			this.numCards = numCards;
			this.cost = cost;
			this.vp = vp;
			this.id = id;
			this.forShuffle = shuffle;
			
		}
	@Override
	public Card clone () {
		Card clone1 = new Victory(this.name, 1, this.cost, 0, this.vp, 0, 0, 0, "", this.id, Gameplay.forShuffle);
		Gameplay.forShuffle++;
		return clone1;
	}
}
