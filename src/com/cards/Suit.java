package com.cards;

public enum Suit {
	Spades, Hearts, Diamonds, Clubs;
	public static Suit getSuit(String suit) {
		switch(suit.toLowerCase()) {
			case ("s"): return Spades;
			case ("h"): return Hearts;
			case ("d"): return Diamonds;
			case ("c"): return Clubs;
			default: throw new RuntimeException("what suit is this?!");
		}
	}
}
