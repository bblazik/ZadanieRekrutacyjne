package bb.carddeck.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.widget.ListView;

import com.android.databinding.library.baseAdapters.BR;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by barte_000 on 11.07.2017.
 */

public class CardList extends BaseObservable{

    @SerializedName("deck_id")
    public String deck_id;

    @SerializedName("cards")
    public List<Card> cardList;

    @SerializedName("success")
    public Boolean success;

    @SerializedName("remaining")
    public Integer remaining = 0;

    public String getDeck_id() {
        return deck_id;
    }

    public void setDeck_id(String deck_id) {
        this.deck_id = deck_id;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    @Bindable
    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
        notifyPropertyChanged(BR.cardList);
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
    @Bindable
    public String getRemaining() {
        return remaining.toString();
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
        notifyPropertyChanged(BR.remaining);
    }
}
