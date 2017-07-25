package bb.carddeck.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.widget.ListView;

import com.android.databinding.library.baseAdapters.BR;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by barte_000 on 11.07.2017.
 */

public class CardList extends BaseObservable{

    @SerializedName("deck_id")
    public String deck_id;

    @SerializedName("cards")
    public List<Card> cardList = new ArrayList<>();

    @SerializedName("success")
    public Boolean success;

    @SerializedName("remaining")
    public Integer remaining = 0;

    public String getDeck_id() {
        return deck_id;
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
