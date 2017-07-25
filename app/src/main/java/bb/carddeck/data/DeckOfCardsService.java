package bb.carddeck.data;


import bb.carddeck.model.CardList;
import bb.carddeck.model.Deck;
import io.reactivex.Observable;
import retrofit2.http.*;

public interface DeckOfCardsService {

    String ENDPOINT = "https://deckofcardsapi.com/";

    /*
    * Return new shuffled deck
    * */
    @GET("api/deck/new/shuffle/")
    Observable<Deck> getShuffledDeck(@retrofit2.http.Query("deck_count") Integer number);

    /*
    * Return list of cards
    *  */
    @GET("api/deck/{deck_id}/draw/")
    Observable<CardList> getCards(@Path(value ="deck_id") String deck_id,
                                  @Query("count") Integer number,
                                  @Query("deck_count") Integer deckNumber);

    /*
    * Return shuffled deck
    * */
    @GET("api/deck/{deck_id}/shuffle")
    Observable<Deck> getShuffle(@Path(value = "deck_id") String deck_id);
}
