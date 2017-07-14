package bb.carddeck.Activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
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

    @BindView(R.id.NumberOfRemainingCards)
    TextView NumOfRemCards;
    @BindView(R.id.NumDecks) TextView NumOfDecks;
    @BindView(R.id.ReshuffleButton) Button reshuffleButton;
    @BindView(R.id.Composition) TextView composition;
    @BindView(R.id.progressBar) ProgressBar progressBar;

    CardsAdapter adapter;
    Deck deck;
    final int NumberOfCards = 5;
    InternetState internetState;
    Integer numberOfDecks;
    CardList cardList;

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

        reshuffleButton.setOnClickListener(listener);

        internetState = new InternetState(getBaseContext());
        if(!internetState.isOnline()) return;

        adapter = new CardsAdapter(DeckDashboard.this, new ArrayList<Card>());
        setListAdapter(adapter);

        setProgressBarState(true);
        PopulateAdapter();
    }

    static String communicate(List<Card> ls){
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

    void PopulateAdapter(){
        deck = Query.GetDeck(numberOfDecks);
        Query.GetCardsAsync(deck.getDeck_id(), NumberOfCards, adapter, DeckDashboard.this);
    }

    public void setTextViews(String remaining, CardList cardList){
        NumOfRemCards.setText(remaining);
        composition.setText(communicate(cardList.getCardList()));
    }

    public void setCardList(CardList cardList){
        this.cardList = cardList;
    }

    public void setProgressBarState(Boolean barState){
        if(barState) progressBar.setVisibility(View.VISIBLE);
        else progressBar.setVisibility(View.GONE);
    }

    public void checkRemaining(CardList cardList){
        if(cardList.getRemaining().equals(0)){
            deck = Query.GetShuffle(deck.getDeck_id());
        }
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!internetState.isOnline()) return;
            setProgressBarState(true);
            if(deck == null){
                PopulateAdapter();
            }else{
                checkRemaining(cardList);
                Query.GetCardsAsync(deck.getDeck_id(), NumberOfCards, adapter, DeckDashboard.this);
            }
        }
    };
}
