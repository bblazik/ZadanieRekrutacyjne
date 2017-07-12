package bb.carddeck.Activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import bb.carddeck.API.Query;
import bb.carddeck.Adapter.CardsAdapter;
import bb.carddeck.R;
import bb.carddeck.model.Card;
import bb.carddeck.model.CardList;
import bb.carddeck.model.Deck;

public class DeckDashboard extends ListActivity{

    ArrayAdapter<Card> adapter;
    CardList cardList ;
    Deck deck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_dashboard);

        //Populate cardList.
        deck = Query.GetDeck(5); //TODO No internet handle deck = null
        cardList =  Query.GetCards(deck.getDeck_id(), 5);


        adapter = new CardsAdapter(this, cardList.getCardList());
        setListAdapter(adapter);
    }
}
