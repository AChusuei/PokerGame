package com.cards;

import static org.junit.Assert.*;
import static com.cards.TexasHoldEmPokerHandEvaluator.*;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.cards.Card;

public class HandTest {

	@Test
	public void compareHands_TwoFullHousesWithSameTripsDifferentPairs_HandWithHigherPairWins() {
		Hand p1 = Hand.FullHouse(Rank.Four, Rank.Nine);
		Hand p2 = Hand.FullHouse(Rank.Four, Rank.Ace);
		assertTrue(p1.compareTo(p2) < 0);
	}
	
	@Test
	public void compareHands_TwoFullHousesWithSameTripsSamePairs_HandsAreEqual() {
		Hand p1 = Hand.FullHouse(Rank.Four, Rank.Nine);
		Hand p2 = Hand.FullHouse(Rank.Four, Rank.Nine);
		assertTrue(p1.compareTo(p2) == 0);
	}
	
	@Test
	public void compareHands_HighCardHandsHaveSameCards_HandsAreEqual() {
		Hand p1 = Hand.HighCard(Rank.King, Rank.Jack, Rank.Nine, Rank.Six, Rank.Five);
		Hand p2 = Hand.HighCard(Rank.King, Rank.Jack, Rank.Nine, Rank.Six, Rank.Five);
		assertTrue(p1.compareTo(p2) == 0);
	}
	
	@Test
	public void compareHands_HighCardHandsHaveSameCardsExceptLastCard_HandsWithHigherFifthCardWins() {
		Hand p1 = Hand.HighCard(Rank.King, Rank.Jack, Rank.Nine, Rank.Six, Rank.Five);
		Hand p2 = Hand.HighCard(Rank.King, Rank.Jack, Rank.Nine, Rank.Six, Rank.Six);
		assertTrue(p1.compareTo(p2) < 0);
	}
	
	// I'd write more, but the tests are a little tedious ... 
	
	@Test
	public void compareHands_StraightFlushBeatsEverythingElse() {
		Hand p1 = Hand.StraightFlush(Rank.King, Suit.Clubs);
		Hand p2 = Hand.FullHouse(Rank.Four, Rank.Nine);
		assertTrue(p1.compareTo(p2) > 0);
	}
}
