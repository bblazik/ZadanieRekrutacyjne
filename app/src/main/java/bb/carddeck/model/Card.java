package bb.carddeck.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by barte_000 on 11.07.2017.
 */

public class Card implements Comparable<Card>{
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

    public int getCardRank(){
        switch (code.charAt(0)){
            case 'A': return 1;
            case '0': return 10;
            case 'J': return 11;
            case 'Q': return 12;
            case 'K': return 13;
            default:
                return Character.getNumericValue(code.charAt(0));
        }
    }

    public int getCardColor(){
        switch (code.charAt(1)){
            case 'H': return 0;
            case 'S': return 1;
            case 'C': return 2;
            case 'D': return 3;
            default: return 0;
        }
    }

    public Card(String code) {
        this.code = code;
    }

    @Override
    public int compareTo(@NonNull Card o) {
        return this.getCardRank() - o.getCardRank();
    }
}
