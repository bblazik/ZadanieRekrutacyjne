package bb.carddeck.ViewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.graphics.drawable.Drawable;
import android.view.View;

import java.io.InputStream;
import java.net.URL;

import bb.carddeck.model.Card;
import bb.carddeck.model.CardList;
import bb.carddeck.model.Deck;


public class CardViewModel extends BaseObservable{
    private Context context;
    private Card card;
    private CardList cardList;
    private Deck deck;


    public CardViewModel(Context context, Card card) {
        this.context = context;
        this.card = card;
    }


    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }
    public String getCode() {
        return card.code;
    }

    public View.OnClickListener onClickedRedrawButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /*
            if(!internetState.isOnline()) return;
            setProgressBarState(true);
            if(deck == null){
                populateAdapter();
            }else{
                checkRemaining(cardList);
                Query.GetCardsAsync(deck.getDeck_id(), NumberOfCards, adapter, DeckDashboard.this);
            }*/
        }
    };
}
