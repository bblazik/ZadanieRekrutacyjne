package bb.carddeck.Activity;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import bb.carddeck.API.API;
import bb.carddeck.API.Query;
import bb.carddeck.R;
import bb.carddeck.model.Card;
import bb.carddeck.model.CardList;
import bb.carddeck.model.Deck;
import bb.carddeck.model.Images;
import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.numberPicker2)
    NumberPicker numberPicker;
    @BindView(R.id.PickNumber)
    Button pickNumber;
    List<Card> cl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        /* Move TO TESTs
        String generalInfoJson = "{\"deck_id\": \"1vwcv8qjn9ry\", \"cards\": [{\"suit\": \"SPADES\", \"images\": {\"png\": \"http://deckofcardsapi.com/static/img/4S.png\", \"svg\": \"http://deckofcardsapi.com/static/img/4S.svg\"}, \"image\": \"http://deckofcardsapi.com/static/img/4S.png\", \"code\": \"4S\", \"value\": \"4\"}], \"success\": true, \"remaining\": 255}";

        JsonParser parser = new JsonParser();
        JsonObject o = parser.parse(generalInfoJson).getAsJsonObject();

        Images images = new Gson().fromJson(o.getAsJsonObject("images"), Images.class);

        Type listType = new TypeToken<List<Card>>(){}.getType();
        List<Card> cards = new Gson().fromJson(o.getAsJsonArray("cards"), listType);

        CardList cl = new Gson().fromJson(o, CardList.class);
        String s = "b";
        */
        pickNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), DeckDashboard.class);
                startActivity(intent);
            }
        });
    }




}
