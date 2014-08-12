package com.cards;

import static org.junit.Assert.*;
import static com.cards.TexasHoldEmPokerHandEvaluator.*;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.cards.Card;

public class TexasHoldEmPokerHandEvaluatorTest {
	
	@Test
	public void determineHighestStraight_whenHandContainsAtoT_returnsAceHighStraight() {
		Hand hand = findHighestStraight(fourFlushWithStraight());
		assertEquals(HandRank.Straight, hand.handRank);
		assertEquals(Rank.Ace, hand.primaryRank);
	}
	
	@Test
	public void determineHighestStraight_whenHandContainsJto7_returnsJackHighStraight() {
		Hand hand = findHighestStraight(sixFlushWithStraight());
		assertEquals(HandRank.Straight, hand.handRank);
		assertEquals(Rank.Jack, hand.primaryRank);
	}
	
	@Test
	public void determineHighestStraight_whenHandContainsAto5_returnsWheelStraight() {
		Hand hand = findHighestStraight(FourFlushWithWheelStraight());
		assertEquals(HandRank.Straight, hand.handRank);
		assertEquals(Rank.Five, hand.primaryRank);
	}
	
	@Test
	public void determineHighestStraight_whenHandContainsTto7FourCardStraight_returnsNull() {
		assertNull(findHighestStraight(nonStraightFiveFlush()));
	}
	
	@Test
	public void determineHighestFlush_ifFourOfSameSuit_returnNull() {
		assertNull(getAllFlushCards(fourFlushWithStraight()));
	}
	
	@Test
	public void determineHighestFlush_ifFiveOfSameSuit_returnsSetOfFiveCards() {
		assertFlushOfSize(getAllFlushCards(nonStraightFiveFlush()), 5);
	}
	
	@Test
	public void determineHighestFlush_ifSixOfSameSuit_returnsSetOfSixCards() {
		assertFlushOfSize(getAllFlushCards(sixFlushWithStraight()), 6);
	}
	
	@Test
	public void determineHighestFlush_ifSevenOfSameSuit_returnsSetOfSevenCards() {
		assertFlushOfSize(getAllFlushCards(sevenFlush()), 7);
	}
	
	@Test
	public void findHighestFlushOrStraight_whenFlushOrStraightPossible_returnsFlush() {
		Hand hand = findHighestFlushOrStraight(sixFlushWithStraight());
		assertEquals(HandRank.Flush, hand.handRank);
		assertEquals(Rank.Jack, hand.primaryRank);
		assertEquals(Rank.Ten, hand.secondaryRank);
		assertEquals(Rank.Nine, hand.thirdRank);
		assertEquals(Rank.Eight, hand.fourthRank);
		assertEquals(Rank.Four, hand.fifthRank);
	}
	
	@Test
	public void findDuplicateTypeHand_whenFourJacks_returnsFourOfaKind() {
		Hand hand = findDuplicateTypeHand(quadsJacksWithTenKicker());
		assertEquals(HandRank.FourOfAKind, hand.handRank);
		assertEquals(Rank.Jack, hand.primaryRank);
		assertEquals(Rank.Ten, hand.secondaryRank);
	}
	
	@Test
	public void findDuplicateTypeHand_whenThreeTensAndThreeTwosExist_returnsFullHouseTensOverTwos() {
		Hand hand = findDuplicateTypeHand(tensFullWithThreeTwos());
		assertEquals(HandRank.FullHouse, hand.handRank);
		assertEquals(Rank.Ten, hand.primaryRank);
		assertEquals(Rank.Two, hand.secondaryRank);
	}
	
	@Test
	public void findDuplicateTypeHand_whenThreeFoursTwoJacksTwoKingsExist_returnsFullHouseFoursOverKings() {
		Hand hand = findDuplicateTypeHand(foursFullWithTwoJacksAndTwoKings());
		assertEquals(HandRank.FullHouse, hand.handRank);
		assertEquals(Rank.Four, hand.primaryRank);
		assertEquals(Rank.King, hand.secondaryRank);
	}
	
	@Test
	public void findDuplicateTypeHand_whenThreeSevensAndAceKingExist_returnsTripSevenswithAKKickers() {
		Hand hand = findDuplicateTypeHand(threeSevensAndAceKing());
		assertEquals(HandRank.ThreeOfAKind, hand.handRank);
		assertEquals(Rank.Three, hand.primaryRank);
		assertEquals(Rank.Ace, hand.secondaryRank);
		assertEquals(Rank.King, hand.thirdRank);
	}
	
	@Test
	public void findDuplicateTypeHand_whenThreePairsExist_returnsHighestTwoPair() {
		Hand hand = findDuplicateTypeHand(twoKingsTwoQueensTwoJacksSix());
		assertEquals(HandRank.TwoPair, hand.handRank);
		assertEquals(Rank.King, hand.primaryRank);
		assertEquals(Rank.Queen, hand.secondaryRank);
		assertEquals(Rank.Jack, hand.thirdRank);
	}
	
	@Test
	public void findDuplicateTypeHand_whenTwoPairsExist_returnsTwoPairPlusHighestKicker() {
		Hand hand = findDuplicateTypeHand(twoSixesTwoFoursAceKicker());
		assertEquals(HandRank.TwoPair, hand.handRank);
		assertEquals(Rank.Six, hand.primaryRank);
		assertEquals(Rank.Four, hand.secondaryRank);
		assertEquals(Rank.Ace, hand.thirdRank);
	}
	
	@Test
	public void findDuplicateTypeHand_whenOnePairExists_returnsOnePairPlusHighestKicker() {
		Hand hand = findDuplicateTypeHand(twoEightsPlusQueenTenSevenKicker());
		assertEquals(HandRank.OnePair, hand.handRank);
		assertEquals(Rank.Eight, hand.primaryRank);
		assertEquals(Rank.Queen, hand.secondaryRank);
		assertEquals(Rank.Ten, hand.thirdRank);
		assertEquals(Rank.Seven, hand.fourthRank);
	}
	
	@Test
	public void findDuplicateTypeHand_whenNoPairExists_returnsHighCardHand() {
		Hand hand = findDuplicateTypeHand(kingQueenNineSixFourThreeTwo());
		assertEquals(HandRank.HighCard, hand.handRank);
		assertEquals(Rank.King, hand.primaryRank);
		assertEquals(Rank.Queen, hand.secondaryRank);
		assertEquals(Rank.Nine, hand.thirdRank);
		assertEquals(Rank.Six, hand.fourthRank);
		assertEquals(Rank.Four, hand.fifthRank);
	}
	
	@Test
	public void findHandFromCardSet_whenHandContainsAtoT_returnsAceHighStraight() {
		Hand hand = findHandFromCardSet(fourFlushWithStraight());
		assertEquals(HandRank.Straight, hand.handRank);
		assertEquals(Rank.Ace, hand.primaryRank);
	}
	
	@Test
	public void findHandFromCardSet_whenHandContainsBothStraightAndFlush_returnsJackHighFlush() {
		Hand hand = findHandFromCardSet(sixFlushWithStraight());
		assertEquals(HandRank.Flush, hand.handRank);
		assertEquals(Rank.Jack, hand.primaryRank);
	}
	
	@Test
	public void findHandFromCardSet_whenHandContainsAto5_returnsWheelStraight() {
		Hand hand = findHandFromCardSet(FourFlushWithWheelStraight());
		assertEquals(HandRank.Straight, hand.handRank);
		assertEquals(Rank.Five, hand.primaryRank);
	}
	
	@Test
	public void findHandFromCardSet_whenFlushOrStraightPossible_returnsFlush() {
		Hand hand = findHandFromCardSet(sixFlushWithStraight());
		assertEquals(HandRank.Flush, hand.handRank);
		assertEquals(Rank.Jack, hand.primaryRank);
		assertEquals(Rank.Ten, hand.secondaryRank);
		assertEquals(Rank.Nine, hand.thirdRank);
		assertEquals(Rank.Eight, hand.fourthRank);
		assertEquals(Rank.Four, hand.fifthRank);
	}
	
	@Test
	public void findHandFromCardSet_whenFourJacks_returnsFourOfaKind() {
		Hand hand = findHandFromCardSet(quadsJacksWithTenKicker());
		assertEquals(HandRank.FourOfAKind, hand.handRank);
		assertEquals(Rank.Jack, hand.primaryRank);
		assertEquals(Rank.Ten, hand.secondaryRank);
	}
	
	@Test
	public void findHandFromCardSet_whenThreeTensAndThreeTwosExist_returnsFullHouseTensOverTwos() {
		Hand hand = findHandFromCardSet(tensFullWithThreeTwos());
		assertEquals(HandRank.FullHouse, hand.handRank);
		assertEquals(Rank.Ten, hand.primaryRank);
		assertEquals(Rank.Two, hand.secondaryRank);
	}
	
	@Test
	public void findHandFromCardSet_whenThreeFoursTwoJacksTwoKingsExist_returnsFullHouseFoursOverKings() {
		Hand hand = findHandFromCardSet(foursFullWithTwoJacksAndTwoKings());
		assertEquals(HandRank.FullHouse, hand.handRank);
		assertEquals(Rank.Four, hand.primaryRank);
		assertEquals(Rank.King, hand.secondaryRank);
	}
	
	@Test
	public void findHandFromCardSet_whenThreeSevensAndAceKingExist_returnsTripSevenswithAKKickers() {
		Hand hand = findHandFromCardSet(threeSevensAndAceKing());
		assertEquals(HandRank.ThreeOfAKind, hand.handRank);
		assertEquals(Rank.Three, hand.primaryRank);
		assertEquals(Rank.Ace, hand.secondaryRank);
		assertEquals(Rank.King, hand.thirdRank);
	}
	
	@Test
	public void findHandFromCardSet_whenThreePairsExist_returnsHighestTwoPair() {
		Hand hand = findHandFromCardSet(twoKingsTwoQueensTwoJacksSix());
		assertEquals(HandRank.TwoPair, hand.handRank);
		assertEquals(Rank.King, hand.primaryRank);
		assertEquals(Rank.Queen, hand.secondaryRank);
		assertEquals(Rank.Jack, hand.thirdRank);
	}
	
	@Test
	public void findHandFromCardSet_whenTwoPairsExist_returnsTwoPairPlusHighestKicker() {
		Hand hand = findHandFromCardSet(twoSixesTwoFoursAceKicker());
		assertEquals(HandRank.TwoPair, hand.handRank);
		assertEquals(Rank.Six, hand.primaryRank);
		assertEquals(Rank.Four, hand.secondaryRank);
		assertEquals(Rank.Ace, hand.thirdRank);
	}
	
	@Test
	public void findHandFromCardSet_whenOnePairExists_returnsOnePairPlusHighestKicker() {
		Hand hand = findHandFromCardSet(twoEightsPlusQueenTenSevenKicker());
		assertEquals(HandRank.OnePair, hand.handRank);
		assertEquals(Rank.Eight, hand.primaryRank);
		assertEquals(Rank.Queen, hand.secondaryRank);
		assertEquals(Rank.Ten, hand.thirdRank);
		assertEquals(Rank.Seven, hand.fourthRank);
	}
	
	@Test
	public void findHandFromCardSet_whenNoPairExists_returnsHighCardHand() {
		Hand hand = findHandFromCardSet(kingQueenNineSixFourThreeTwo());
		assertEquals(HandRank.HighCard, hand.handRank);
		assertEquals(Rank.King, hand.primaryRank);
		assertEquals(Rank.Queen, hand.secondaryRank);
		assertEquals(Rank.Nine, hand.thirdRank);
		assertEquals(Rank.Six, hand.fourthRank);
		assertEquals(Rank.Four, hand.fifthRank);
	}
	
	@Test
	public void findHandFromCardSet_whenThreeFoursAndFlushExist_returnsFlush() {
		Hand hand = findHandFromCardSet(threeFoursWithFlush());
		assertEquals(HandRank.Flush, hand.handRank);
		assertEquals(Rank.Nine, hand.primaryRank);
		assertEquals(Rank.Six, hand.secondaryRank);
		assertEquals(Rank.Four, hand.thirdRank);
		assertEquals(Rank.Three, hand.fourthRank);
		assertEquals(Rank.Two, hand.fifthRank);
	}
	
	private void assertFlushOfSize(List<Card> cardList, int number) {
		assertEquals(number, cardList.size());
		Suit expectedSuit = null;
		for (Card card : cardList) {
			if (expectedSuit == null) {
				expectedSuit = card.suit;
				continue;
			}
			assertEquals(expectedSuit, card.suit);
		}
	}
	
	private Set<Card> fourFlushWithStraight() {
		return convertToCardSet("AS,KS,QS,JS,TC,KC,QC");
	}
	
	private Set<Card> nonStraightFiveFlush() {
		return convertToCardSet("TS,7S,9S,4S,8S,KC,QC");
	}
	
	private Set<Card> sixFlushWithStraight() {
		return convertToCardSet("JS,4S,8S,3S,9S,TS,7H");
	}
	
	private Set<Card> FourFlushWithWheelStraight() {
		return convertToCardSet("JD,4S,8S,3S,5S,AC,2H");
	}
	
	private Set<Card> sevenFlush() {
		return convertToCardSet("2S,6S,8S,3S,9S,KS,JS");
	}
	
	private Set<Card> quadsJacksWithTenKicker() {
		return convertToCardSet("JH,4S,JC,3S,JD,TS,JS");
	}
	
	private Set<Card> tensFullWithThreeTwos() {
		return convertToCardSet("TS,2S,TC,3S,2D,TH,2H");
	}
	
	private Set<Card> foursFullWithTwoJacksAndTwoKings() {
		return convertToCardSet("KD,4S,4C,KS,JD,4H,JH");
	}
	
	private Set<Card> threeSevensAndAceKing() {
		return convertToCardSet("3S,AS,KD,9S,TD,3C,3H");
	}
	
	private Set<Card> twoKingsTwoQueensTwoJacksSix() {
		return convertToCardSet("KS,6S,KD,JS,QD,QC,JH");
	}
	
	private Set<Card> twoSixesTwoFoursAceKicker() {
		return convertToCardSet("4S,6S,6D,TS,4D,QC,AH");
	}
	
	private Set<Card> twoEightsPlusQueenTenSevenKicker() {
		return convertToCardSet("2S,7S,TD,8S,QD,8C,5H");
	}
	
	private Set<Card> kingQueenNineSixFourThreeTwo() {
		return convertToCardSet("3S,6S,2D,QS,KD,4C,9H");
	}
	
	private Set<Card> threeFoursWithFlush() {
		return convertToCardSet("3D,6D,2D,4S,4D,4C,9D");
	}
	
	
	
}
