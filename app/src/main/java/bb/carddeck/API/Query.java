package bb.carddeck.API;

import android.util.Log;

import java.io.IOException;

import bb.carddeck.model.CardList;
import bb.carddeck.model.Deck;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by barte_000 on 11.07.2017.
 */

public class Query {
    static API.APIInterface apiInterface = API.getClient();

    public static Deck GetDeck(Integer numberOfDecks){

        Deck newDeck = null;
        Call<Deck> query = apiInterface.GetShuffledDeck(numberOfDecks);
        try {
            newDeck = query.execute().body();
            return newDeck != null ? newDeck:null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static CardList GetCards(String deck_id, Integer numberOfCards){

        CardList newCardList = null;
        Call<CardList> query = apiInterface.GetCards(deck_id,numberOfCards);
        try {
            newCardList = query.execute().body();
            return newCardList != null ? newCardList :null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
