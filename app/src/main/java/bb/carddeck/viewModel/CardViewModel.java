package bb.carddeck.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;

import bb.carddeck.data.DataManager;
import bb.carddeck.view.Adapter.CardAdapter;
import bb.carddeck.CardDeckApplication;
import bb.carddeck.logic.DeckComposition;
import bb.carddeck.R;
import bb.carddeck.model.CardList;
import bb.carddeck.model.Deck;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;


public class CardViewModel extends BaseObservable{
    private Context context;

    public CardList mCardList = new CardList();
    public ObservableField<Boolean> progressBarVisible = new ObservableField<>();
    public final ObservableField<Integer> numberOfDecks = new ObservableField();
    private final int numberOfCards = 5;

    private DataManager mDataManager;
    private CardAdapter mCardAdapter;
    private CompositeDisposable mCompositeDisposable;

    public CardViewModel(Context context, CardAdapter cardAdapter, Integer numberOfDecks) {
        this.context = context;
        mCompositeDisposable = new CompositeDisposable();
        mDataManager = CardDeckApplication.get(this.context).getComponent().dataManager();
        mCardAdapter = cardAdapter;
        this.numberOfDecks.set(numberOfDecks);
        getCardList("new", numberOfCards);
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
        mCompositeDisposable.add(mDataManager.getCards(deckId,numberOfCards, numberOfDecks.get())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(mDataManager.getScheduler())
                .subscribeWith(new DisposableObserver<CardList>() {
                    @Override
                    public void onNext(@NonNull CardList cardList) {
                        mCardList = cardList;
                        mCardAdapter.setCardList(cardList.cardList); //TODO Check if there is a better way
                        notifyChange();
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
                        getCardList(deck.deck_id, numberOfCards);
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
        if(cardList.remaining == 0){
            getShuffle(cardList.getDeck_id());
            return true;
        }else
            return false;
    }

    public String communicate(){
        StringBuilder sb = new StringBuilder();
        DeckComposition deckComposition = new DeckComposition(mCardList.cardList);

        if(deckComposition.containsColor()){
            sb.append(context.getString(R.string.color));
        }
        if (deckComposition.containsStairs()) {
            sb.append(context.getString(R.string.stairs));
        }
        if(deckComposition.containsThreeFigures()){
            sb.append(context.getString(R.string.figures));
        }
        if(deckComposition.containsTwins()){
            sb.append(context.getString(R.string.twins));
        }
        if(sb.toString().equals(""))
            return context.getString(R.string.composition);

        return sb.toString();
    }

    public String getNumberOfDecks(){
        return numberOfDecks.get().toString();
    }
}
