package com.cards;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CardSetStats {
	
	public final Rank quadsRank;
	public final List<Rank> tripsList;
	public final List<Rank> pairList;
	public final List<Rank> rankList;
	
	public CardSetStats(Collection<Card> cardCollection) {
    	// Find dupes
    	Map<Rank, Set<Card>> rankMap = new HashMap<Rank, Set<Card>>();
    	for (Card card : cardCollection) {
    		Set<Card> dupeSet = rankMap.get(card.rank);
    		if (dupeSet == null) {
    			dupeSet = new HashSet<Card>();
    		}
    		dupeSet.add(card);
    		rankMap.put(card.rank, dupeSet);
    	}
    	Rank qr = null;
    	tripsList = new ArrayList<Rank>();
    	pairList = new ArrayList<Rank>();
    	Set<Rank> rankSet = new HashSet<Rank>();
    	for (Rank rank : rankMap.keySet()) {
    		switch (rankMap.get(rank).size()) {
    			case 4: qr = rank; break;
    			case 3: tripsList.add(rank); break;
    			case 2: pairList.add(rank); break;
    		}
    	    rankSet.add(rank);
    	}
    	quadsRank = qr;
    	rankList = new ArrayList<Rank>(rankSet);
    	Collections.sort(tripsList);
    	Collections.sort(pairList);
    	Collections.sort(rankList);
	}
	
	public List<Rank> getRemainingHighCards(Rank rankToRemove) {
		List<Rank> list = new ArrayList<Rank>();
		list.add(rankToRemove);
		return getRemainingHighCards(list);
	}
	
	public List<Rank> getRemainingHighCards(List<Rank> ranksToRemove) {
		List<Rank> newList = new ArrayList<Rank>(rankList);
		newList.removeAll(ranksToRemove);
		Collections.sort(newList);
		return newList;
    }
}
