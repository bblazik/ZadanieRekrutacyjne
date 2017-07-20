package bb.carddeck.model;

import android.databinding.ObservableField;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by barte_000 on 11.07.2017.
 */

public class CardList{

    @SerializedName("deck_id")
    public String deck_id;

    @SerializedName("cards")
    public List<Card> cardList;

    @SerializedName("success")
    public Boolean success;

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

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }
}
