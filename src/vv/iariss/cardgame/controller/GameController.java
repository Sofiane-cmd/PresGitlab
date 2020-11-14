package vv.iariss.cardgame.controller;

import vv.iariss.cardgame.model.Deck;
import vv.iariss.cardgame.model.Player;
import vv.iariss.cardgame.model.PlayingCard;
import vv.iariss.cardgame.view.View;

import java.util.ArrayList;

public class GameController {
    enum GameState{
        AddingPlayers,
        CardsDealt,
        WinnerRevealed
    }

    private Deck deck;
    private ArrayList<Player> players;
    private Player winner;
    private View view;
    private GameState gameState;

    public GameController(View view, Deck deck) {
        this.view = view;
        this.deck = deck;
        players = new ArrayList<Player>();
        gameState = GameState.AddingPlayers;
        view.setController(this);
    }

    public void run() {
        while (true) {
            switch (gameState) {
                case AddingPlayers:
                    view.promptForPlayerName();
                    break;
                case CardsDealt:
                    view.promptForFlip();
                    break;
                case WinnerRevealed:
                    view.promptForNewGame();
                    break;
            }
        }
    }

    public void addPlayer(String playerName) {
        if (gameState == GameState.AddingPlayers) {
            players.add(new Player(playerName));
            view.showPlayerName(players.size(), playerName);
        }
    }

    public void startGame() {
        if (gameState != GameState.CardsDealt) {
            deck.shuffle();
            int playerIndex = 1;
            for (Player player : players) {
                player.addCardToHand(deck.removeTopCard());
                view.showFaceDownCardForPlayer(playerIndex++, player.getName());
            }
            gameState = GameState.CardsDealt;
        }
    }

    public void flipCard() {
        int playerIndex = 1;
        for (Player player: players) {
            PlayingCard pc = player.getCard(0);
            pc.flip();
            view.showCardForPlayer(playerIndex++, player.getName(),
                    pc.getRank().toString(), pc.getSuit().toString());
        }

        evaluateWinner();
        displayWinner();
        rebuildDeck();

        gameState = GameState.WinnerRevealed;
    }

    public void evaluateWinner() {
        Player bestPlayer = null;
        int bestRank = -1;
        int bestSuit = -1;

        for (Player player : players) {
            boolean newBestPlayer = false;

            if (bestPlayer == null) {
                newBestPlayer = true;
            }

            else {
                PlayingCard pc = player.getCard(0);
                int thisRank = pc.getRank().value();
                if (thisRank >= bestRank) {
                    if (thisRank > bestRank) {
                        newBestPlayer = true;
                    } else {
                        if (pc.getSuit().value() > bestSuit) {
                            newBestPlayer = true;
                        }
                    }
                }
            }

            if (newBestPlayer) {
                bestPlayer = player;
                PlayingCard pc = player.getCard(0);
                bestRank = pc.getRank().value();
                bestSuit = pc.getSuit().value();
            }
        }

        winner = bestPlayer;
    }

    public void displayWinner() {
        view.showWinner(winner.getName());
    }

    public void rebuildDeck() {
        for (Player player : players) {
            deck.returnCardToDeck(player.removeCard());
        }
    }
}
