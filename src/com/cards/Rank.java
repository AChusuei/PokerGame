package com.cards;

public enum Rank implements Comparable<Rank> {

	Ace(14,'A'),
	King(13,'K'),
	Queen(12,'Q'),
	Jack(11,'J'),
	Ten(10,'T'),
	Nine(9,'9'),
	Eight(8,'8'),
	Seven(7,'7'),
	Six(6,'6'),
	Five(5,'5'),
	Four(4,'4'),
	Three(3,'3'),
	Two(2,'2'),
	Null(0,'0');
	
	public final int value;
	public final char sn;
	
	private Rank(int value, char shortName) {
		this.value = value;
		this.sn = shortName;
	}
}
