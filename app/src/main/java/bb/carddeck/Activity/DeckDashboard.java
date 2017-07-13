package bb.carddeck.Activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import bb.carddeck.API.Query;
import bb.carddeck.Adapter.CardsAdapter;
import bb.carddeck.Logic.Composition;
import bb.carddeck.Logic.InternetState;
import bb.carddeck.R;
import bb.carddeck.model.Card;
import bb.carddeck.model.CardList;
import bb.carddeck.model.Deck;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DeckDashboard extends ListActivity{

    @BindView(R.id.NumberOfRemainingCards) TextView NumOfRemCards;
    @BindView(R.id.NumDecks) TextView NumOfDecks;
    @BindView(R.id.ReshuffleButton) Button reshuffleButton;
    @BindView(R.id.Composition) TextView composition;

    CardsAdapter adapter;
    CardList cardList ;
    Deck deck;
    final int NumberOfCards = 5;
    InternetState internetState;
    Integer numberOfDecks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_dashboard);
        ButterKnife.bind(this);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Intent intent = getIntent();
        numberOfDecks = intent.getIntExtra("numberOfDecks", 1);
        NumOfDecks.setText(numberOfDecks.toString());

        reshuffleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!internetState.isOnline()) {
                    Toast.makeText(getBaseContext(), "Please enable you internet connection", Toast.LENGTH_LONG).show();
                    return;
                }

                if(deck == null){
                    deck = Query.GetDeck(numberOfDecks);
                    cardList = Query.GetCards(deck.getDeck_id(), NumberOfCards);
                    adapter = new CardsAdapter(DeckDashboard.this, cardList.getCardList());
                    setListAdapter(adapter);
                }

                if(cardList.getRemaining().equals(0)){
                    deck = Query.GetShuffle(deck.getDeck_id());
                }

                cardList =  Query.GetCards(deck.getDeck_id(), 5);
                NumOfRemCards.setText(cardList.getRemaining().toString());
                adapter.refreshData(cardList.getCardList());
                composition.setText(communicate(cardList.getCardList()));
            }
        });


        internetState = new InternetState(getBaseContext());
        if(!internetState.isOnline()) {
            Toast.makeText(getBaseContext(), "Please enable you internet connection", Toast.LENGTH_LONG).show();
            return;
        }

        //Populate cardList.
        deck = Query.GetDeck(numberOfDecks);
        cardList = Query.GetCards(deck.getDeck_id(), NumberOfCards);

        NumOfRemCards.setText(cardList.getRemaining().toString());
        composition.setText(communicate(cardList.getCardList()));

        adapter = new CardsAdapter(this, cardList.getCardList());
        setListAdapter(adapter);


    }

    String communicate(List<Card> ls){
        StringBuilder sb = new StringBuilder();
        Composition c = new Composition();

        if(c.ContainsColor(ls)){
            sb.append("Color! ");
        }
        if (c.ContainsStairs(ls)) {
            sb.append("Stairs! ");
        }
        if(c.ContainsThreeFigures(ls)){
            sb.append("Three figures! ");
        }
        if(c.ContainsTwins(ls)){
            sb.append("Twins! ");
        }
        if(sb.toString().equals(""))
            return "Unfortunately nothing matches";

        return sb.toString();
    }
}
