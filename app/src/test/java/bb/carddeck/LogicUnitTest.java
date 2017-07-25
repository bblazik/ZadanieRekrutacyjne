package bb.carddeck;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import bb.carddeck.Logic.DeckComposition;
import bb.carddeck.model.Card;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/**
 * Created by barte_000 on 13.07.2017.
 */

public class LogicUnitTest {

    @Test
    public void GetCardRankNumberTest() throws Exception {
        assertEquals(DeckComposition.GetCardRank(new Card("8C")),8);
    }

    @Test
    public void GetCardRankFigureTest() throws Exception {
        assertEquals(DeckComposition.GetCardRank(new Card("AC")),1);
    }

    @Test
    public void GetCardColor() throws Exception {
        assertEquals(DeckComposition.GetCardColor(new Card("AC")), 2);
    }

    @Test
    public void ContainsColor() throws Exception {
        //COLOR, TWINS
        List<Card> c = new ArrayList<>();
        c.add(new Card("8C"));
        c.add(new Card("8C"));
        c.add(new Card("9H"));
        c.add(new Card("AD"));
        c.add(new Card("8C"));
        assertTrue(DeckComposition.containsColor(c));
    }

    @Test
    public void NotContainsColor() throws Exception {
        //COLOR, TWINS
        List<Card> c = new ArrayList<>();
        c.add(new Card("8S"));
        c.add(new Card("8C"));
        c.add(new Card("9H"));
        c.add(new Card("AD"));
        c.add(new Card("8C"));
        assertFalse(DeckComposition.containsColor(c));
    }

    @Test
    public void ContainsSameRanks() throws Exception{
        List<Card> c = new ArrayList<>();
        c.add(new Card("8C"));
        c.add(new Card("8C"));
        c.add(new Card("9H"));
        c.add(new Card("AD"));
        c.add(new Card("8C"));
        assertTrue(DeckComposition.containsTwins(c));
    }

    @Test
    public void NotContainsSameRanks() throws Exception{
        List<Card> c = new ArrayList<>();
        c.add(new Card("8C"));
        c.add(new Card("8C"));
        c.add(new Card("9H"));
        c.add(new Card("AD"));
        c.add(new Card("9D"));
        assertFalse(DeckComposition.containsTwins(c));
    }

    @Test
    public void ContainsThreeFigures() throws Exception{
        List<Card> c = new ArrayList<>();
        c.add(new Card("JC"));
        c.add(new Card("QC"));
        c.add(new Card("KH"));
        c.add(new Card("AD"));
        c.add(new Card("8C"));
        assertTrue(DeckComposition.containsThreeFigures(c));
    }

    @Test
    public void NotContainsThreeFigures() throws Exception{
        List<Card> c = new ArrayList<>();
        c.add(new Card("8C"));
        c.add(new Card("8C"));
        c.add(new Card("9H"));
        c.add(new Card("AD"));
        c.add(new Card("9D"));
        assertFalse(DeckComposition.containsThreeFigures(c));
    }

    @Test
    public void ContainsStairs() throws Exception {
        List<Card> c = new ArrayList<>();
        c.add(new Card("JC"));
        c.add(new Card("QC"));
        c.add(new Card("KH"));
        c.add(new Card("AD"));
        c.add(new Card("8C"));
        assertTrue(DeckComposition.containsStairs(c));
    }

    @Test
    public void NotContainsStairs() throws Exception {
        List<Card> c = new ArrayList<>();
        c.add(new Card("8C"));
        c.add(new Card("8C"));
        c.add(new Card("9H"));
        c.add(new Card("AD"));
        c.add(new Card("9D"));
        assertFalse(DeckComposition.containsStairs(c));
    }

    @Test
    public void IsSorted() throws Exception {
        List<Card> c = new ArrayList<>();
        c.add(new Card("JC"));
        c.add(new Card("QC"));
        c.add(new Card("KH"));
        c.add(new Card("AD"));
        c.add(new Card("8C"));

        DeckComposition.SortCards(c);
        assertEquals(c.get(0).getCode(), "AD");
        assertEquals(c.get(1).getCode(), "8C");
        assertEquals(c.get(2).getCode(), "JC");
        assertEquals(c.get(3).getCode(), "QC");
        assertEquals(c.get(4).getCode(), "KH");
    }
}
