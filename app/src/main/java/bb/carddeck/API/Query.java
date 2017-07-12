package bb.carddeck.API;

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
    static CardList newCardList;

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

    public static Deck GetShuffle(String deck_id){
        Deck newDeck = null;
        Call<Deck> query = apiInterface.GetShuffle(deck_id);
        try {
            newDeck = query.execute().body();
            return newDeck != null ? newDeck:null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void GetCardsAsync (String deck_id, Integer numberOfCards){

        Call<CardList> query = apiInterface.GetCards(deck_id, numberOfCards);
        query.enqueue(new Callback<CardList>() {
            @Override
            public void onResponse(Call<CardList> call, Response<CardList> response) {
                newCardList = response.body();
            }

            @Override
            public void onFailure(Call<CardList> call, Throwable t) {
            String s = t.toString();
            }
        });
    }
}
