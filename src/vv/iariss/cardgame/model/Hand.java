package vv.iariss.cardgame.model;

import java.util.ArrayList;

public class Hand {
    private ArrayList<PlayingCard> cards;

    public Hand() {
        cards = new ArrayList<PlayingCard>();
    }

    public void addCard(PlayingCard pc) {
        cards.add(pc);
    }

    public PlayingCard getCards(int index) {
        return cards.get(index);
    }

    public PlayingCard removeCard(){
        return cards.remove(0);
    }
}
