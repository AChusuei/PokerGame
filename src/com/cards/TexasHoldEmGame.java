package com.cards;

import static com.cards.TexasHoldEmPokerHandEvaluator.*;

public class TexasHoldEmGame {
	
	/*
	 *  There are three arguments, player one's pocket cards, player two's pocket cards, and the community board.
	 *  The evaluator combines each player's cards with the community board and determines which player can make the better poker hand with the community board.
	 *  e.g. if the parameters are "Ac,Ad" "Kd,Qs" "Ah,Kh,Qd,Qc,3h" (pocket Aces versus King Queen)
	 *  The result looks like this:
	 *      P1 has FullHouse, Aces full of Queens
	 *      P2 has FullHouse, Queens full of Kings
	 *      P1 wins!
	 */
	public static void main(String[] args) {
		Hand p1Hand = findHandFromCardSet(convertToCardSet(args[0].trim(), args[2].trim()));
		Hand p2Hand = findHandFromCardSet(convertToCardSet(args[1].trim(), args[2].trim()));
		System.out.println("Player one has " + p1Hand.toString());
		System.out.println("Player two has " + p2Hand.toString());
		if (p1Hand.compareTo(p2Hand) == 0) {
			System.out.println("Player one has the same hand as player two!");
		} else {
			System.out.println((p1Hand.compareTo(p2Hand) > 0 ? "P1" : "P2") + " wins!");
		}
	}
	
}
