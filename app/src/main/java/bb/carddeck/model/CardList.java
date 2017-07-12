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

    @SerializedName("success")
    Boolean success;

    @SerializedName("remaining")
    Integer remaining;



    public String getDeck_id() {
        return deck_id;
    }

    public void setDeck_id(String deck_id) {
        this.deck_id = deck_id;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public CardList(String deck_id, List<Card> cardList, Integer remaining) {
        this.deck_id = deck_id;
        this.cardList = cardList;
        this.remaining = remaining;
    }

    public CardList(String deck_id, List<Card> cardList, Integer remaining, Boolean success) {
        this.deck_id = deck_id;
        this.cardList = cardList;
        this.remaining = remaining;
        this.success = success;
    }
}
