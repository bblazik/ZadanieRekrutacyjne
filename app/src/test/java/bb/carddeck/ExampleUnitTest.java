package bb.carddeck;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.lang.reflect.Type;
import java.util.List;

import bb.carddeck.model.Card;
import bb.carddeck.model.CardList;
import bb.carddeck.model.Images;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void jsonParsing() throws Exception {

        String generalInfoJson = "{\"deck_id\": \"1vwcv8qjn9ry\", \"cards\": [{\"suit\": \"SPADES\", \"images\": {\"png\": \"http://deckofcardsapi.com/static/img/4S.png\", \"svg\": \"http://deckofcardsapi.com/static/img/4S.svg\"}, \"image\": \"http://deckofcardsapi.com/static/img/4S.png\", \"code\": \"4S\", \"value\": \"4\"}], \"success\": true, \"remaining\": 255}";

        JsonParser parser = new JsonParser();
        JsonObject o = parser.parse(generalInfoJson).getAsJsonObject();

        Type listType = new TypeToken<List<Card>>(){}.getType();
        List<Card> cards = new Gson().fromJson(o.getAsJsonArray("cards"), listType);

        CardList cl = new Gson().fromJson(o, CardList.class);
        assert(cl != null);
        assertEquals(cards.get(0).getCode(),"4S");
        assertEquals(cards.get(0).getImg().getPng(), "http://deckofcardsapi.com/static/img/4S.png");

    }
}