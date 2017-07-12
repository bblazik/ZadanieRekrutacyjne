package bb.carddeck.model;

/**
 * Created by barte_000 on 11.07.2017.
 */

public class Deck {
    String deck_id;
    Boolean success;
    Integer remaining;
    Boolean shuffled;

    public Deck(String deck_id, Boolean success, Integer remaining, Boolean shuffled) {
        this.deck_id = deck_id;
        this.success = success;
        this.remaining = remaining;
        this.shuffled = shuffled;
    }

    public String getDeck_id() {
        return deck_id;
    }

    public void setDeck_id(String deck_id) {
        this.deck_id = deck_id;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }

    public Boolean getShuffled() {
        return shuffled;
    }

    public void setShuffled(Boolean shuffled) {
        this.shuffled = shuffled;
    }
}


