package vv.iariss.cardgame.model;

public class Player {
    private String name;
    private Hand hand;

    public Player(String name){
        this.name = name;
        this.hand = new Hand();
    }

    public String getName() {
        return name;
    }

    public void addCardToHand(PlayingCard pc) {
        hand.addCard(pc);
    }

    public PlayingCard getCard(int index) {
        return hand.getCards(index);
    }

    public PlayingCard removeCard() {
        return hand.removeCard();
    }
}
