package com.cards;

public enum HandRank {
	
	HighCard(0),
	OnePair(1),
	TwoPair(2),
	ThreeOfAKind(3),
	Straight(4),
	Flush(5),
	FullHouse(6),
	FourOfAKind(7),
	StraightFlush(8);
	
	int value;
	
	private HandRank(int value) {
		this.value = value;
	}
	
}
