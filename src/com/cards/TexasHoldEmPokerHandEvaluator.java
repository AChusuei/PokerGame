package com.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TexasHoldEmPokerHandEvaluator {

	public static Hand findHandFromCardSet(Set<Card> cardSet) {
		// Look for flush and straight possibilities first, for if they exist, it is impossible for other five card hands to 
		// exist that beat either a straight, flush, or straight flush.
		Hand straightOrFlush = findHighestFlushOrStraight(cardSet);
		if (straightOrFlush != null) {
			return straightOrFlush;
		} else {
			return findDuplicateTypeHand(cardSet);
		}
	}
	
	public static Hand findHighestFlushOrStraight(Set<Card> cardSet) {
		List<Card> flushCards = getAllFlushCards(cardSet);
		if (flushCards != null) {
			Suit suit = flushCards.get(0).suit;
			// If we find straight with flush cards, we have a straight flush
			Hand sfHand = findHighestStraight(new HashSet<Card>(flushCards)); 
			if (sfHand != null) {
				return Hand.StraightFlush(sfHand.primaryRank, suit);
			} else {
				return Hand.Flush(flushCards, suit);
			}
		} else {
			return findHighestStraight(cardSet);
		}
	}
	
    public static Hand findHighestStraight(Set<Card> cardSet) {
    	CardSetStats stats = new CardSetStats(cardSet);
    	if (stats.rankList.size() < 5) {
			return null;
		}
    	Rank pointerRank = null;
    	List<Rank> straight = new ArrayList<Rank>();
    	boolean containsAce = false; // used for wheel straight
    	for (Rank nextRank : stats.rankList) {
    		if (nextRank == Rank.Ace) {
    			containsAce = true;
    		}
    		if (pointerRank != null && pointerRank.value - 1 != nextRank.value) {
    			straight = new ArrayList<Rank>();
    		}
    		straight.add(nextRank);
			pointerRank = nextRank;
			if (straight.size() == 5) {
				return Hand.Straight(straight.get(0));
			}
			// Check for wheel 
			if (straight.size() == 4 && straight.get(0) == Rank.Five && containsAce) {
				return Hand.Straight(Rank.Five);
			}
    	}
    	return null;
    }
 
    /*
	 * Note for the purposes of finding a straight flush that doesn't include the highest card,
	 * the flush returned here may include more than five cards. 
	 */
	protected static List<Card> getAllFlushCards(Set<Card> cardSet) {
		if (cardSet.size() < 5) {
			return null;
		}
    	// Note that we could return more than 5 cards here.
    	Map<Suit, List<Card>> suitMap = new HashMap<Suit, List<Card>>();
    	for (Card card : cardSet) {
    		List<Card> flushLists = suitMap.get(card.suit);
    		if (flushLists == null) {
    			flushLists = new ArrayList<Card>();
    		}
    		flushLists.add(card);
    		suitMap.put(card.suit, flushLists);
    	}
    	for (Suit suit : suitMap.keySet()) {
    		 /* 
    		  * This presumes the first flush we find is the
    		  * definitive flush. Since we don't have more than seven cards, 
    		  * two five-flush hands are impossible.
    		  */
    		if (suitMap.get(suit).size() > 4) {
    			Collections.sort(suitMap.get(suit));
    			return suitMap.get(suit);
    		}
    	}
    	return null;
    }
    
	public static Hand findDuplicateTypeHand(Set<Card> cardSet) { // quads, trips, pairs, or high cards
    	CardSetStats stats = new CardSetStats(cardSet);
    	// quads?
    	if (stats.quadsRank != null) {
    		List<Rank> remainingCards = stats.getRemainingHighCards(stats.quadsRank);
			return Hand.FourOfAKind(stats.quadsRank, remainingCards.get(0));
		}
    	// trips or full house?
    	if (stats.tripsList.size() > 0) {
    		Rank tripsRank = stats.tripsList.get(0);
    		if (stats.tripsList.size() > 1) {
    			// if there's another trips here, by definition there can't be other pairs.
    			Rank fhPair = stats.tripsList.get(1);
    			return Hand.FullHouse(tripsRank, fhPair);
    		} else {
    			// Look for pairs here 
    			if (stats.pairList.size() > 0) {
    				return Hand.FullHouse(tripsRank, stats.pairList.get(0));
    			} else {
    				List<Rank> remainingCards = stats.getRemainingHighCards(tripsRank);
    				return Hand.ThreeOfAKind(tripsRank, remainingCards.get(0), remainingCards.get(1));
    			}
    		}
    	}
    	// one or two pair?
    	if (stats.pairList.size() > 0) {
    		Rank highPairRank = stats.pairList.get(0);
    		if (stats.pairList.size() > 1) { // two pair
    			Rank secondPairRank = stats.pairList.get(1);
    			List<Rank> remainingCards = stats.getRemainingHighCards(stats.pairList.subList(0, 2));
    			return Hand.TwoPair(highPairRank, secondPairRank, remainingCards.get(0));
    		} else { // one pair
    			List<Rank> remainingCards = stats.getRemainingHighCards(highPairRank);
    			return Hand.OnePair(highPairRank, remainingCards.get(0), remainingCards.get(1), remainingCards.get(2));
    		}
    	}
    	// only high card hand possible
    	return Hand.HighCard(stats.rankList.get(0), stats.rankList.get(1), stats.rankList.get(2), stats.rankList.get(3), stats.rankList.get(4));
    }

	public static Set<Card> convertToCardSet(String player, String community) {
		return convertToCardSet(player + "," + community);
	}
	
	public static Set<Card> convertToCardSet(String cards) {
		Set<Card> set = new HashSet<Card>();
		String[] array = (cards.split(","));
		for (String card : array) {
			set.add(new Card(card));
		}
		return set;
	}
	
}
