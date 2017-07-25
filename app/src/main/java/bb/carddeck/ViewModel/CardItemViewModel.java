package bb.carddeck.ViewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import bb.carddeck.model.Card;

public class CardItemViewModel extends BaseObservable {

    private Card mCard;
    private Context mContext;

    public CardItemViewModel(Card card, Context context){
        mCard = card;
        mContext = context;
    }

    public String getCode(){
        return mCard.code;
    }

    public String getPicture(){
        return mCard.imageUrl;
    }

    @BindingAdapter("imageUrl")
    public static void getImg(ImageView imageView, String url){
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public void setCard(Card card) {
        mCard = card;
        notifyChange();
    }
}
