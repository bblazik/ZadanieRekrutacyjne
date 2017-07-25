package bb.carddeck.Logic;

import java.util.Collections;
import java.util.List;

import bb.carddeck.model.Card;

public class DeckComposition {

    List<Card> mCardList;

    public DeckComposition(List<Card> list){
        mCardList = list;
    }

    public Boolean containsColor(){
        int[] colors = new int[4];
        for (Card c: mCardList) {
            colors[c.getCardColor()]++;
            if(colors[c.getCardColor()] >= 3) return true;
        }
        return false;
    }

    public Boolean containsStairs(){
        int series = 1;
        Collections.sort(mCardList);
        for(int i = 0; i<mCardList.size()-1; i++){
            if(Math.abs(mCardList.get(i).getCardRank() - mCardList.get(i+1).getCardRank()) == 1){
                series++;
                if(series >= 3) return true;
            }else{
                series = 1;
            }
        }
        return false;
    }

    public Boolean containsThreeFigures(){
        int figures = 0;
        for (Card c: mCardList) {
            if(c.code.charAt(0) == 'J' || c.code.charAt(0) == 'Q' || c.code.charAt(0) == 'K')
            {
                figures++;
                if(figures >= 3) return true;
            }
        }
        return false;
    }

    public Boolean containsTwins(){
        int[] ranks = new int[13];
        for (Card c: mCardList) {
            ranks[c.getCardRank()-1]++;
            if(ranks[c.getCardRank()-1] >=3) return true;
        }
        return false;
    }
}
