package com.cards;

public class Card implements Comparable<Card> {
	
	public final Suit suit;
	public final Rank rank;

	public Card(String card) {
		this(card.charAt(0) + "", card.charAt(1) + "");
	}
	
	public Card(String rank, String suit) {
		this.suit = Suit.getSuit(suit);
		switch (rank.toLowerCase()) {
		    case "2": this.rank = Rank.Two; break;
	    	case "3": this.rank = Rank.Three; break;
	    	case "4": this.rank = Rank.Four; break;
	    	case "5": this.rank = Rank.Five; break;
	    	case "6": this.rank = Rank.Six; break;
	    	case "7": this.rank = Rank.Seven; break;
	    	case "8": this.rank = Rank.Eight; break;
	    	case "9": this.rank = Rank.Nine; break;
	    	case "t": this.rank = Rank.Ten; break; 
	    	case "j": this.rank = Rank.Jack; break;
			case "q": this.rank = Rank.Queen; break;
			case "k": this.rank = Rank.King; break;
			case "a": this.rank = Rank.Ace; break;
			default: throw new RuntimeException("Rank not recognized!!!");
		}
	}

	@Override // reverse order sort 
	public int compareTo(Card that) {
		if (this.rank.value < that.rank.value) return 1;
		if (this.rank.value > that.rank.value) return -1;
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + rank.value;
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (rank != other.rank)
			return false;
		if (suit != other.suit)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return rank + " of " + suit;
	}
}
