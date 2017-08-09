package bb.carddeck;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bb.carddeck.logic.DeckComposition;
import bb.carddeck.model.Card;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/**
 * Created by barte_000 on 13.07.2017.
 */

public class DeckCompositionUnitTest {
    DeckComposition deckComposition;

    @Test
    public void getCardRankNumberTest() throws Exception {

        assertEquals(new Card("8C").getCardRank(),8);
    }

    @Test
    public void GetCardRankFigureTest() throws Exception {
        assertEquals(new Card("AC").getCardRank(),1);
    }

    @Test
    public void GetCardColor() throws Exception {
        assertEquals(new Card("AC").getCardColor(), 2);
    }

    @Test
    public void ContainsColor() throws Exception {
        //COLOR, TWINS
        List<Card> c = new ArrayList<>();
        c.add(new Card("8S"));
        c.add(new Card("8S"));
        c.add(new Card("9H"));
        c.add(new Card("AS"));
        c.add(new Card("8S"));

        deckComposition = new DeckComposition(c);
        assertTrue(deckComposition.containsColor());
    }

    @Test
    public void NotContainsColorIfOnlyTwoCardsOfSameColor() throws Exception {
        //COLOR, TWINS
        List<Card> c = new ArrayList<>();
        c.add(new Card("8S"));
        c.add(new Card("8C"));
        c.add(new Card("9H"));
        c.add(new Card("AD"));
        c.add(new Card("8C"));

        deckComposition = new DeckComposition(c);
        assertFalse(deckComposition.containsColor());
    }

    @Test
    public void ContainsSameRanks() throws Exception{
        List<Card> c = new ArrayList<>();
        c.add(new Card("8C"));
        c.add(new Card("8C"));
        c.add(new Card("9H"));
        c.add(new Card("AD"));
        c.add(new Card("8C"));
        deckComposition = new DeckComposition(c);
        assertTrue(deckComposition.containsTwins());
    }

    @Test
    public void NotContainsSameRanks() throws Exception{
        List<Card> c = new ArrayList<>();
        c.add(new Card("8C"));
        c.add(new Card("8C"));
        c.add(new Card("9H"));
        c.add(new Card("AD"));
        c.add(new Card("9D"));

        deckComposition = new DeckComposition(c);
        assertFalse(deckComposition.containsTwins());
    }

    @Test
    public void ContainsThreeFigures() throws Exception{
        List<Card> c = new ArrayList<>();
        c.add(new Card("JC"));
        c.add(new Card("QC"));
        c.add(new Card("KH"));
        c.add(new Card("AD"));
        c.add(new Card("8C"));
        deckComposition = new DeckComposition(c);
        assertTrue(deckComposition.containsThreeFigures());
    }

    @Test
    public void NotContainsThreeFigures() throws Exception{
        List<Card> c = new ArrayList<>();
        c.add(new Card("8C"));
        c.add(new Card("8C"));
        c.add(new Card("9H"));
        c.add(new Card("AD"));
        c.add(new Card("9D"));

        deckComposition = new DeckComposition(c);
        assertFalse(deckComposition.containsThreeFigures());
    }

    @Test
    public void ContainsStairs() throws Exception {
        List<Card> c = new ArrayList<>();
        c.add(new Card("JC"));
        c.add(new Card("QC"));
        c.add(new Card("KH"));
        c.add(new Card("AD"));
        c.add(new Card("8C"));

        deckComposition = new DeckComposition(c);
        assertTrue(deckComposition.containsStairs());
    }

    @Test
    public void NotContainsStairs() throws Exception {
        List<Card> c = new ArrayList<>();
        c.add(new Card("8C"));
        c.add(new Card("7C"));
        c.add(new Card("8H"));
        c.add(new Card("JD"));
        c.add(new Card("QD"));

        deckComposition = new DeckComposition(c);
        assertFalse(deckComposition.containsStairs());
    }

    @Test
    public void IsSorted() throws Exception {
        List<Card> c = new ArrayList<>();
        c.add(new Card("JC"));
        c.add(new Card("QC"));
        c.add(new Card("KH"));
        c.add(new Card("AD"));
        c.add(new Card("8C"));

        Collections.sort(c);

        assertEquals(c.get(0).code, "AD");
        assertEquals(c.get(1).code, "8C");
        assertEquals(c.get(2).code, "JC");
        assertEquals(c.get(3).code, "QC");
        assertEquals(c.get(4).code, "KH");
    }
}
