package bb.carddeck.ViewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;

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

import java.util.List;


public class CardViewModel extends BaseObservable{
    private Context context;
    public CardList mCardList = new CardList();
    public ObservableField<Boolean> progressBarVisible = new ObservableField<>();

    private DataManager mDataManager;
    private CardAdapter mCardAdapter;
    private CompositeDisposable mCompositeDisposable;
    private final int numberOfCards = 15;


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
        getCardList("new", numberOfCards);
        Toast.makeText(context, "Create ViewModel", Toast.LENGTH_SHORT).show();
    }

    public View.OnClickListener onClickedRedrawButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(mCardList!=null){
                shuffleIfRemainingEqualsZero(mCardList);
                getCardList(mCardList.deck_id, numberOfCards);
            }
            else
                getCardList("new", numberOfCards);
        }
    };
    void getCardList(String deckId, Integer numberOfCards){
        progressBarVisible.set(true);
        mCompositeDisposable.add(mDataManager.getCards(deckId,numberOfCards)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(mDataManager.getScheduler())
                .subscribeWith(new DisposableObserver<CardList>() {
                    @Override
                    public void onNext(@NonNull CardList cardList) {

                        mCardList = cardList;
                        notifyChange();

                        mCardAdapter.setItems(cardList.getCardList());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(context, "There was a problem to get cards", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        progressBarVisible.set(false);
                    }
                }));
    }

    void getShuffle(String deckId){
        mCompositeDisposable.add(mDataManager.getShuffleDeck(deckId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(mDataManager.getScheduler())
                .subscribeWith(new DisposableObserver<Deck>() {
                    @Override
                    public void onNext(@NonNull Deck deck) {
                        getCardList(deck.getDeck_id(), numberOfCards);
                        Toast.makeText(context, "Cards get shuffled ", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(context, "Error while shuffle " + e.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    public Boolean shuffleIfRemainingEqualsZero(CardList cardList){
        if(cardList.getRemaining() == "0"){ // eq by value
            getShuffle(cardList.getDeck_id());
            return true;
        }else
            return false;
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
}
