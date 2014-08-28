package com.cards;

import java.util.ArrayList;
import java.util.List;

public class Hand implements Comparable<Hand> {

	final public HandRank handRank;
	final public Suit suit;
	final public List<Rank> ranks;
	
	public Hand(HandRank handRank, Suit suit, Rank primaryRank, Rank secondaryRank, Rank thirdRank, Rank fourthRank, Rank fifthRank) {
		this.handRank = handRank;
		this.suit = suit;
		this.ranks = new ArrayList<Rank>();
		ranks.add(primaryRank);
		ranks.add(secondaryRank);
		ranks.add(thirdRank);
		ranks.add(fourthRank);
		ranks.add(fifthRank);
	}
	
	public Hand(HandRank handRank, Suit suit, List<Rank> ranks) {
		this.handRank = handRank;
		this.suit = suit;
		this.ranks = ranks;
	}
	
	public static Hand HighCard(List<Rank> ranks) {
		return new Hand(HandRank.HighCard, null, ranks);
	}
	
	public static Hand HighCard(Rank primaryRank, Rank secondaryRank, Rank thirdRank, Rank fourthRank, Rank fifthRank) {
		return new Hand(HandRank.HighCard, null, primaryRank, secondaryRank, thirdRank, fourthRank, fifthRank);
	}
	
	public static Hand OnePair(Rank pairRank, Rank firstKicker, Rank secondKicker, Rank thirdKicker) {
		return new Hand(HandRank.OnePair, null, pairRank, firstKicker, secondKicker, thirdKicker, Rank.Null);
	}
	
	public static Hand TwoPair(Rank firstPairRank, Rank secondPairRank, Rank firstKicker) {
		return new Hand(HandRank.TwoPair, null, firstPairRank, secondPairRank, firstKicker, Rank.Null, Rank.Null);
	}
	
	public static Hand ThreeOfAKind(Rank tripsRank, Rank firstKicker, Rank secondKicker) {
		return new Hand(HandRank.ThreeOfAKind, null, tripsRank, firstKicker, secondKicker, Rank.Null, Rank.Null);
	}
	
	public static Hand Straight(Rank highCard) {
		return new Hand(HandRank.Straight, null, highCard, Rank.Null, Rank.Null, Rank.Null, Rank.Null);
	}
	
	public static Hand Flush(List<Card> cards, Suit suit) {
		return new Hand(HandRank.Flush, suit, cards.get(0).rank, cards.get(1).rank, cards.get(2).rank, cards.get(3).rank, cards.get(4).rank);
	}
	
	public static Hand FullHouse(Rank tripsRank, Rank pairRank) {
		return new Hand(HandRank.FullHouse, null, tripsRank, pairRank, Rank.Null, Rank.Null, Rank.Null);
	}
	
	public static Hand FourOfAKind(Rank quadsRank, Rank firstKicker) {
		return new Hand(HandRank.FourOfAKind, null, quadsRank, firstKicker, Rank.Null, Rank.Null, Rank.Null);
	}
	
	public static Hand StraightFlush(Rank highCard, Suit suit) {
		return new Hand(HandRank.StraightFlush, suit, highCard, Rank.Null, Rank.Null, Rank.Null, Rank.Null);
	}

	@Override 
	public String toString() {
		switch (handRank) {
			case StraightFlush: return ranks.get(0) + " high " + handRank + " of " + suit;
			case FourOfAKind: return "Four " + ranks.get(0) + "s, " + ranks.get(1) + " kicker";
			case FullHouse: return handRank + ", " + ranks.get(0) + "s full of " + ranks.get(1) + "s";
			case Flush: return ranks.get(0) + " high " + handRank + " of " + suit;
			case Straight: return ranks.get(0) + " high " + handRank;
			case ThreeOfAKind: return "Three " + ranks.get(0) + "s, " + ranks.get(1).shortName + ranks.get(2).shortName + " kicker";
			case TwoPair: return "Two pair, " + ranks.get(0) + "s and " + ranks.get(1) + "s, " + ranks.get(2) + " kicker";
			case OnePair: return "One pair, " + ranks.get(0) + "s, " + ranks.get(1).shortName + ranks.get(2).shortName + ranks.get(3).shortName + " kicker";
			case HighCard: return ranks.get(0) + " high (" + ranks.get(0).shortName + ranks.get(1).shortName + ranks.get(2).shortName + ranks.get(3).shortName + ranks.get(4).shortName + ")";
			default: throw new RuntimeException("unknown hand rank: " + handRank);
		}
	}
	
	@Override
	public int compareTo(Hand that) {
		int hr = this.handRank.value - that.handRank.value;
		if (hr == 0) {
			for (int index = 0; index < this.ranks.size(); index++) {
				int rankCompare = this.ranks.get(index).value - that.ranks.get(index).value;
				if (rankCompare == 0) {
					continue;
				} else {
					return rankCompare; 
				}
			}
			return 0; 
		} else {
			return hr;
		}
	}
	
}
