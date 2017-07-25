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
    public String suit;
    @SerializedName("images")
    public Images img;
    @SerializedName("image")
    public String imageUrl;
    @SerializedName("code")
    public String code;
    @SerializedName("value")
    public String value;
}
