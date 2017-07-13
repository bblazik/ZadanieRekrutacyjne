package bb.carddeck;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import bb.carddeck.Logic.Composition;
import bb.carddeck.model.Card;
import static org.junit.Assert.*;
/**
 * Created by barte_000 on 13.07.2017.
 */

public class LogicUnitTest {

    @Test
    public void GetCardRankNumberTest() throws Exception {
        assertEquals(Composition.GetCardRank(new Card("8C")),8);
    }

    @Test
    public void GetCardRankFigureTest() throws Exception {
        assertEquals(Composition.GetCardRank(new Card("AC")),1);
    }

    @Test
    public void GetCardColor() throws Exception {
        assertEquals(Composition.GetCardColor(new Card("AC")), 2);
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
        assertTrue(Composition.IsColor(c));
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
        assertFalse(Composition.IsColor(c));
    }

    @Test
    public void ContainsSameRanks() throws Exception{
        List<Card> c = new ArrayList<>();
        c.add(new Card("8C"));
        c.add(new Card("8C"));
        c.add(new Card("9H"));
        c.add(new Card("AD"));
        c.add(new Card("8C"));
        assertTrue(Composition.ContainsTwins(c));
    }

    @Test
    public void NotContainsSameRanks() throws Exception{
        List<Card> c = new ArrayList<>();
        c.add(new Card("8C"));
        c.add(new Card("8C"));
        c.add(new Card("9H"));
        c.add(new Card("AD"));
        c.add(new Card("9D"));
        assertFalse(Composition.ContainsTwins(c));
    }

    @Test
    public void ContainsThreeFigures() throws Exception{
        List<Card> c = new ArrayList<>();
        c.add(new Card("JC"));
        c.add(new Card("QC"));
        c.add(new Card("KH"));
        c.add(new Card("AD"));
        c.add(new Card("8C"));
        assertTrue(Composition.ContainsThreeFigures(c));
    }

    @Test
    public void NotContainsThreeFigures() throws Exception{
        List<Card> c = new ArrayList<>();
        c.add(new Card("8C"));
        c.add(new Card("8C"));
        c.add(new Card("9H"));
        c.add(new Card("AD"));
        c.add(new Card("9D"));
        assertFalse(Composition.ContainsThreeFigures(c));
    }

    @Test
    public void ContainsStairs() throws Exception {
        List<Card> c = new ArrayList<>();
        c.add(new Card("JC"));
        c.add(new Card("QC"));
        c.add(new Card("KH"));
        c.add(new Card("AD"));
        c.add(new Card("8C"));
        assertTrue(Composition.ContainsStairs(c));
    }

    @Test
    public void NotContainsStairs() throws Exception {
        List<Card> c = new ArrayList<>();
        c.add(new Card("8C"));
        c.add(new Card("8C"));
        c.add(new Card("9H"));
        c.add(new Card("AD"));
        c.add(new Card("9D"));
        assertFalse(Composition.ContainsStairs(c));
    }
}
