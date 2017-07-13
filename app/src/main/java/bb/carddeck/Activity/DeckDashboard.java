package bb.carddeck.Activity;

import android.app.ListActivity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import bb.carddeck.API.Query;
import bb.carddeck.Adapter.CardsAdapter;
import bb.carddeck.R;
import bb.carddeck.model.Card;
import bb.carddeck.model.CardList;
import bb.carddeck.model.Deck;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DeckDashboard extends ListActivity{

    @BindView(R.id.NumberOfRemainingCards) TextView NumOfRemCards;
    @BindView(R.id.NumDecks)
    TextView NumOfDecks;
    @BindView(R.id.ReshuffleButton)
    Button reshuffleButton;

    CardsAdapter adapter;
    CardList cardList ;
    Deck deck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_dashboard);
        ButterKnife.bind(this);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Intent intent = getIntent();
        Integer numberOfDecks = intent.getIntExtra("numberOfDecks", 1);

        NumOfDecks.setText(numberOfDecks.toString());

        //Populate cardList.
        deck = Query.GetDeck(numberOfDecks); //TODO No internet handle deck = null
        cardList =  Query.GetCards(deck.getDeck_id(), 5);
        NumOfRemCards.setText(cardList.getRemaining().toString());


        reshuffleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cardList.getRemaining().equals(0)){
                    deck = Query.GetShuffle(deck.getDeck_id());
                }

                cardList =  Query.GetCards(deck.getDeck_id(), 5);
                NumOfRemCards.setText(cardList.getRemaining().toString());
                adapter.refreshData(cardList.getCardList());
            }
        });

        adapter = new CardsAdapter(this, cardList.getCardList());
        setListAdapter(adapter);
    }
}
