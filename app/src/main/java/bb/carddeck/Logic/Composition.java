package bb.carddeck.Logic;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import bb.carddeck.model.Card;
import bb.carddeck.model.CardList;

/**
 * Created by barte_000 on 13.07.2017.
 */

public abstract class Composition {

    public static Boolean ContainsColor(List<Card> ls){
        int[] colors = new int[4];

        for (Card c: ls) {
            colors[GetCardColor(c)]++;
            if(colors[GetCardColor(c)] >= 3) return true;
        }
        /* //TODO check for optimalization
        for(int i: colors)
            if(i>= 3) return true;
        */
        return false;
    }

    public static Boolean ContainsStairs(List<Card> ls){
        int series = 1;

        Collections.sort(ls, new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return Composition.GetCardRank(o1) - Composition.GetCardRank(o2);
            }
        });

        for(int i = 0; i<ls.size()-1; i++){
            if(Math.abs(GetCardRank(ls.get(i)) - GetCardRank(ls.get(i+1)))  == 1){
                series++;
                if(series >= 3) return true;
            }
        }
        return false;
    }

    public static Boolean ContainsThreeFigures(List<Card> ls){
        int figures = 0;

        for (Card c: ls) {
            if(        c.getCode().charAt(0) == 'J'
                    || c.getCode().charAt(0) == 'Q'
                    || c.getCode().charAt(0) == 'K')
            {
                figures++;
                if(figures >= 3)
                    return true;
            }
        }
        return false;
    }

    public static Boolean ContainsTwins(List<Card> ls){
        int[] ranks = new int[13];
        for (Card c: ls) {
            ranks[GetCardRank(c)-1]++;
            if(ranks[GetCardRank(c)-1] >=3) return true;
        }
        return false;
    }

    public static int GetCardRank(Card c){
        switch (c.getCode().charAt(0)){
            case 'A': return 1;
            case '0': return 10;
            case 'J': return 11;
            case 'Q': return 12;
            case 'K': return 13;
            default:
                return Character.getNumericValue(c.getCode().charAt(0));
        }
    }

    public static int GetCardColor(Card c){
        switch (c.getCode().charAt(1)){
            case 'H': return 0;
            case 'S': return 1;
            case 'C': return 2;
            case 'D': return 3;
            default: return 0;
        }
    }
}
