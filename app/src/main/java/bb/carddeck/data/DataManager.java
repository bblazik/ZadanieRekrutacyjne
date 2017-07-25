package bb.carddeck.data;

import android.content.Context;

import bb.carddeck.CardDeckApplication;
import bb.carddeck.Injection.Component.DaggerDataManagerComponent;
import bb.carddeck.Injection.Module.DataManagerModule;
import bb.carddeck.model.CardList;
import bb.carddeck.model.Deck;
import io.reactivex.Observable;

import javax.inject.Inject;

import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


public class DataManager {

    @Inject protected DeckOfCardsService mDeckOfCardsService;
    @Inject protected Scheduler mSubscribeScheduler;

    public DataManager(Context context){
        injectDependencies(context);
    }

    public DataManager(DeckOfCardsService service, Scheduler subscribeScheduler){
        mDeckOfCardsService = service;
        mSubscribeScheduler = subscribeScheduler;
    }

    protected void injectDependencies(Context context){
        DaggerDataManagerComponent.builder()
                .applicationComponent(CardDeckApplication.get(context).getComponent())
                .dataManagerModule(new DataManagerModule())
                .build()
                .inject(this);
    }

    public Scheduler getScheduler(){
        return mSubscribeScheduler;
    }

    public Observable<Deck> getDeck(int numberOFDecks){
        return mDeckOfCardsService.getShuffledDeck(numberOFDecks)
                .concatMap(new Function<Deck, ObservableSource<? extends Deck>>() {
                    @Override
                    public ObservableSource<? extends Deck> apply(@NonNull Deck deck) throws Exception {
                        return deck != null ? Observable.just(deck) : Observable.<Deck>empty();
                   }
                });
    }

    public Observable<Deck> getShuffleDeck(String deckId){
        return mDeckOfCardsService.getShuffle(deckId)
                .concatMap(new Function<Deck, ObservableSource<? extends Deck>>() {
                    @Override
                    public ObservableSource<? extends Deck> apply(@NonNull Deck deck) throws Exception {
                        return deck != null ? Observable.just(deck): Observable.<Deck>empty();
                    }
                });
    }

    public Observable<CardList> getCards(String deckId, Integer numberOfCards, Integer numberOfDecks){
        return mDeckOfCardsService.getCards(deckId, numberOfCards, numberOfDecks)
                .concatMap(new Function<CardList, ObservableSource<? extends CardList>>() {
                    @Override
                    public ObservableSource<? extends CardList> apply(@NonNull CardList cardList) throws Exception {
                        return cardList != null ? Observable.just(cardList) : Observable.<CardList>empty();
                    }
                });
    }
}
