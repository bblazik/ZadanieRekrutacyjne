package bb.carddeck.ViewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;

import bb.carddeck.API.DataManager;
import bb.carddeck.Adapter.CardAdapter;
import bb.carddeck.CardDeckApplication;
import bb.carddeck.Logic.Composition;
import bb.carddeck.model.Card;
import bb.carddeck.model.CardList;
import bb.carddeck.model.Deck;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


public class CardViewModel{
    private Context context;
    private Card card;
    public CardList mCardList;

    public  ObservableField<String> rem = new ObservableField<>();

    private DataManager mDataManager;
    private CardAdapter mCardAdapter;
    private CompositeDisposable mCompositeDisposable;


    public CardViewModel(Context context) {
        this.context = context;
        mCompositeDisposable = new CompositeDisposable();
        mDataManager = CardDeckApplication.get(this.context).getComponent().dataManager();

        Toast.makeText(context, "Create ViewModel", Toast.LENGTH_SHORT).show();
    }

    public CardViewModel(Context context, CardAdapter cardAdapter) {
        this.context = context;
        mCompositeDisposable = new CompositeDisposable();
        mDataManager = CardDeckApplication.get(this.context).getComponent().dataManager();
        mCardAdapter = cardAdapter;
        getCardList("new", 5);
        Toast.makeText(context, "Create ViewModel", Toast.LENGTH_SHORT).show();
    }

    public String getCode() {
        return card.code;
    }
    public Drawable getImg() {
        return LoadImageFromWebOperations(card.getImageUrl());
    }


    public View.OnClickListener onClickedRedrawButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /*
            if(!internetState.isOnline()) return;
            setProgressBarState(true);
            if(deck == null){
                populateAdapter();
            }else{
                checkRemaining(cardList);
                Query.GetCardsAsync(deck.getDeck_id(), NumberOfCards, adapter, DeckDashboard.this);
            }*/

            if(mCardList!=null)
                getCardList(mCardList.deck_id, 5);
            else
                getCardList("new", 5);

            Toast.makeText(context, "Click!", Toast.LENGTH_SHORT).show();
        }
    };
    void getCardList(String deckId, Integer numberOfCards){
        CardDeckApplication cardDeckApplication = CardDeckApplication.get(context);

        mCompositeDisposable.add(mDataManager.getCards(deckId,numberOfCards)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(mDataManager.getScheduler())
                .subscribeWith(new DisposableObserver<CardList>() {
                    @Override
                    public void onNext(@NonNull CardList cardList) {
                        //setProgressBarState(false);
                        mCardList = cardList;

                        rem.set(mCardList.getRemaining().toString());
                        mCardAdapter.setItems(cardList.getCardList());

                        //Toast.makeText(context, "CardlistSize: " + cardList.getCardList().size(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(context, "There was a problem to get cards", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    /*
    public void setProgressBarState(Boolean barState){
        if(barState) progressBar.setVisibility(View.VISIBLE);
        else progressBar.setVisibility(View.GONE);
    }*/


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
}
