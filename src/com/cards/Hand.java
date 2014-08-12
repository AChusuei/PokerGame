package com.cards;

import java.util.List;

public class Hand implements Comparable<Hand> {

	final public HandRank handRank;
	final public Suit suit;
	final public Rank primaryRank;
	final public Rank secondaryRank;
	final public Rank thirdRank;
	final public Rank fourthRank;
	final public Rank fifthRank;
	
	public Hand(HandRank handRank, Suit suit, Rank primaryRank, Rank secondaryRank, Rank thirdRank, Rank fourthRank, Rank fifthRank) {
		this.handRank = handRank;
		this.suit = suit;
		this.primaryRank = primaryRank;
		this.secondaryRank = secondaryRank;
		this.thirdRank = thirdRank;
		this.fourthRank = fourthRank;
		this.fifthRank = fifthRank;
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
			case StraightFlush: return primaryRank + " high " + handRank + " of " + suit;
			case FourOfAKind: return "Four " + primaryRank + "s, " + secondaryRank + " kicker";
			case FullHouse: return handRank + ", " + primaryRank + "s full of " + secondaryRank + "s";
			case Flush: return primaryRank + " high " + handRank + " of " + suit;
			case Straight: return primaryRank + " high " + handRank;
			case ThreeOfAKind: return "Three " + primaryRank + "s, " + secondaryRank.sn + thirdRank.sn + " kicker";
			case TwoPair: return "Two pair, " + primaryRank + "s and " + secondaryRank + "s, " + thirdRank + " kicker";
			case OnePair: return "One pair, " + primaryRank + "s, " + secondaryRank.sn + thirdRank.sn + fourthRank.sn + " kicker";
			case HighCard: return primaryRank + " high (" + primaryRank.sn + secondaryRank.sn + thirdRank.sn + fourthRank.sn + fifthRank.sn + ")";
			default: throw new RuntimeException("unknown hand rank: " + handRank);
		}
	}
	
	@Override
	public int compareTo(Hand that) {
		int hr = this.handRank.value - that.handRank.value;
		if (hr == 0) {
			int one = this.primaryRank.value - that.primaryRank.value;
			if (one == 0) {
				int two = this.secondaryRank.value - that.secondaryRank.value;
				if (two == 0) {
					int three = this.thirdRank.value - that.thirdRank.value;
					if (three == 0) {
						int four = this.fourthRank.value - that.fourthRank.value;
						if (four == 0) {
							int five = this.fifthRank.value - that.fifthRank.value;
							if (five == 0) return 0;
							else return five;
						}
						return four;
					}
					return three;
				}
				return two;
			}
			return one;
		} 
		return hr;
	}
	
}
