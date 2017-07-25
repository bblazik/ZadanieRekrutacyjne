package bb.carddeck.model;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import bb.carddeck.Logic.Composition;

/**
 * Created by barte_000 on 11.07.2017.
 */

public class Card{
    @SerializedName("suit")
    String suit;
    @SerializedName("images")
    Images img;
    @SerializedName("image")
    public String imageUrl;
    @SerializedName("code")
    public String code;
    @SerializedName("value")
    String value;

    public Card(String imageUrl, String value, String suit, String code, Images img) {
        this.imageUrl = imageUrl;
        this.value = value;
        this.suit = suit;
        this.code = code;
        this.img = img;
    }

    public Card(String code) {
        this.code = code;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Images getImg() {
        return img;
    }

    public void setImg(Images img) {
        this.img = img;
    }

}
