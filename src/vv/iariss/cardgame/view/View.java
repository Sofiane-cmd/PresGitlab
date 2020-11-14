package vv.iariss.cardgame.view;

import vv.iariss.cardgame.controller.GameController;

import java.util.Scanner;

public class View {
    private GameController controller;
    private Scanner keyboard = new Scanner(System.in);

    public void setController(GameController gc) {
        this.controller = gc;
    }

    public void promptForPlayerName() {
        System.out.println("Enter Player Name : ");
        String name = keyboard.nextLine();
        if (name.isEmpty()) {
            controller.startGame();
        } else {
            controller.addPlayer(name);
        }
    }

    public void promptForFlip() {
//        Press enter to reveal cards
//        nexLine
//        flipCard
    }

    public void promptForNewGame() {
//        Press enter to deal again
//        nexLine
//        startgame
    }

    public void showPlayerName(int playerIndex, String playerName) {
//    [playerIndex][playerName]
    }

    public void showFaceDownCardForPlayer(int playerIndex, String playerName) {
//    [playerName][][]
    }

    public void showCardForPlayer(int playerIndex, String playerName, String rank, String suit) {
//    [playerName][rank][suit]
    }

    public void showWinner(String winnerName) {
        System.out.println("Winner ! " + winnerName);
    }
}
