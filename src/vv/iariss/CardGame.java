package vv.iariss;

import vv.iariss.cardgame.controller.GameController;
import vv.iariss.cardgame.model.Deck;
import vv.iariss.cardgame.view.View;

public class CardGame {

    public static void main(String[] args) {
        GameController controller = new GameController(new View(), new Deck());
        controller.run();
    }
}
