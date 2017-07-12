package bb.carddeck.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by barte_000 on 12.07.2017.
 */

public class Images {
    @SerializedName("png")
    String png;
    @SerializedName("svg")
    String svg;

    public String getPng() {
        return png;
    }

    public void setPng(String png) {
        this.png = png;
    }

    public String getSvg() {
        return svg;
    }

    public void setSvg(String svg) {
        this.svg = svg;
    }

    public Images(String png, String svg) {
        this.png = png;
        this.svg = svg;
    }
}

