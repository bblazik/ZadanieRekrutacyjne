package bb.carddeck.Activity;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bb.carddeck.API.DataManager;
import bb.carddeck.Adapter.CardAdapter;
import bb.carddeck.CardDeckApplication;
import bb.carddeck.Logic.Composition;
import bb.carddeck.Logic.InternetState;
import bb.carddeck.R;
import bb.carddeck.model.Card;
import bb.carddeck.model.CardList;
import bb.carddeck.model.Deck;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.Subject;
import io.reactivex.subscribers.*;

public class DeckDashboard extends ListActivity{

    @BindView(R.id.NumberOfRemainingCards)
    TextView NumOfRemCards;
    @BindView(R.id.NumDecks) TextView NumOfDecks;
    @BindView(R.id.ReshuffleButton) Button reshuffleButton;
    @BindView(R.id.Composition) TextView composition;
    @BindView(R.id.progressBar) ProgressBar progressBar;

    Deck mDeck;
    final int NumberOfCards = 5;
    InternetState internetState;
    Integer numberOfDecks;
    CardList cardList;
    List<Card> ls;

    private DataManager mDataManager;
    private CardAdapter mCardAdapter;
    CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deck_dashboard);
        ButterKnife.bind(this);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Intent intent = getIntent();
        numberOfDecks = intent.getIntExtra("numberOfDecks", 1);
        NumOfDecks.setText(numberOfDecks.toString());

        internetState = new InternetState(getBaseContext());
        if(!internetState.isOnline()) return;

        mCompositeDisposable = new CompositeDisposable();
        mDataManager = CardDeckApplication.get(this).getComponent().dataManager();
        //ls =
        mCardAdapter = new CardAdapter(this, new ArrayList<Card>());
        setListAdapter(mCardAdapter);

        //getDeck();
        getCardList("new", 5);
        /*
        adapter = new CardsAdapter(DeckDashboard.this, new ArrayList<Card>());
        setListAdapter(adapter);

        setProgressBarState(true);
        populateAdapter();
        */
    }

    void getDeck(Integer n) {
        mCompositeDisposable.add(mDataManager.getDeck(n)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(mDataManager.getScheduler())
                .subscribeWith(new DisposableObserver<Deck>() {
                    @Override
                    public void onNext(@NonNull Deck deck) {
                        mDeck = deck;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {


                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    void getCardList(String deckId, Integer numberOfCards){
        mCompositeDisposable.add(mDataManager.getCards(deckId,numberOfCards)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(mDataManager.getScheduler())
        .subscribeWith(new DisposableObserver<CardList>() {
            @Override
            public void onNext(@NonNull CardList cardList) {
                mCardAdapter.setItems(cardList.getCardList());
                Toast.makeText(getBaseContext(), "CardlistSize: " + cardList.getCardList().size(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        }));
    }


    static String communicate(List<Card> ls){
        StringBuilder sb = new StringBuilder();
        Composition c = new Composition();

        if(Composition.ContainsColor(ls)){
            sb.append("Color! ");
        }
        if (Composition.ContainsStairs(ls)) {
            sb.append("Stairs! ");
        }
        if(Composition.ContainsThreeFigures(ls)){
            sb.append("Three figures! ");
        }
        if(Composition.ContainsTwins(ls)){
            sb.append("Twins! ");
        }
        if(sb.toString().equals(""))
            return "Unfortunately nothing matches";

        return sb.toString();
    }
    /*
    void populateAdapter(){
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
    }*/
/*
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!internetState.isOnline()) return;
            setProgressBarState(true);
            if(deck == null){
                populateAdapter();
            }else{
                checkRemaining(cardList);
                Query.GetCardsAsync(deck.getDeck_id(), NumberOfCards, adapter, DeckDashboard.this);
            }
        }
    };
    */
}
