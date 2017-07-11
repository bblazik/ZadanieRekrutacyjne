package bb.carddeck.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by barte_000 on 11.07.2017.
 */

public class CardList {

    @SerializedName("deck_id")
    String deck_id;

    @SerializedName("cards")
    private List<Card> cardList;

    @SerializedName("remaining")
    Integer remaining;

}
