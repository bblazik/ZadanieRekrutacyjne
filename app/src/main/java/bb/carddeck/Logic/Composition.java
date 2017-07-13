package bb.carddeck.Logic;

import java.util.List;

import bb.carddeck.model.Card;
import bb.carddeck.model.CardList;

/**
 * Created by barte_000 on 13.07.2017.
 */

public class Composition {
    CardList cardList;
    int[] ranks = new int[13];
    int[] colors = new int[4];

    public Composition(CardList cardList) {
        this.cardList = cardList;
    }

    Boolean color(List<Card> ls){
        for (Card c: ls) {
            colors[GetCardColor(c)]++;
        }
        for(int i: colors)
            if(i>= 3) return true;

        return false;
    }

    Boolean stairs(List<Card> ls){
        return true;
    }

    Boolean figures(List<Card> ls){
        return true;
    }

    Boolean twins(List<Card> ls){
        return true;
    }

    int GetCardRank(Card c){
        switch (c.getCode().charAt(0)){
            case 'A': return 1;
            case 'J': return 11;
            case 'Q': return 12;
            case 'K': return 13;
            default:
                return c.getCode().charAt(0);
        }
    }

    int GetCardColor(Card c){
        switch (c.getSuit().charAt(0)){
            case 'H': return 0;
            case 'S': return 1;
            case 'C': return 2;
            case 'D': return 3;
            default: return 0;
        }
    }
}
