package bb.carddeck.model;

import android.graphics.drawable.Drawable;

import com.google.gson.annotations.SerializedName;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by barte_000 on 11.07.2017.
 */

public class Card {
    Drawable image;

    @SerializedName("image")
    String imageUrl;
    String value;
    String suit;
    String code;

    public Card(String imageUrl, String value, String suit, String code) {
        this.image = LoadImageFromWebOperations(imageUrl);
        this.value = value;
        this.suit = suit;
        this.code = code;
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
}
