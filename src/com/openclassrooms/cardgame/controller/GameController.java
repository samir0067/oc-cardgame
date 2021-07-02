package com.openclassrooms.cardgame.controller;

import com.openclassrooms.cardgame.games.GameEvaluator;
import com.openclassrooms.cardgame.model.Deck;
import com.openclassrooms.cardgame.model.Player;
import com.openclassrooms.cardgame.model.PlayingCard;
import com.openclassrooms.cardgame.view.GameViewable;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    enum GameState {
        AddingPlayers, CardsDealt, WinnerRevealed
    }

    Deck deck;
    List<Player> players;
    Player winner;
    GameViewable viewable;
    GameState gameState;
    GameEvaluator evaluator;

    public GameController(Deck deck, GameViewable viewable, GameEvaluator evaluator) {
        this.deck = deck;
        this.viewable = viewable;
        this.players = new ArrayList<Player>();
        this.gameState = GameState.AddingPlayers;
        this.evaluator = evaluator;
        viewable.setController(this);
    }

    public void addPlayer(String result) {
    }

    public void run() {
        while (gameState == GameState.AddingPlayers) {
            viewable.promptForPlayerName();
        }

        switch (gameState) {
            case CardsDealt:
                viewable.promptForFlip();
                break;
            case WinnerRevealed:
                viewable.promptForNewGame();
                break;
        }
    }

    public void addPLayer(String playerName) {
        if (gameState == GameState.AddingPlayers) {
            players.add(new Player(playerName));
            viewable.showPlayerName(players.size(), playerName);
        }
    }

    public void startGame() {
        if (gameState != GameState.CardsDealt) {
            deck.shuffle();
            int playerIndex = 1;
            for (Player player : players) {
                player.addCardToHand(deck.removeTopCard());
                viewable.showFaceDownCardForPlayer(playerIndex++, player.getName());
            }
            gameState = GameState.CardsDealt;
        }
        this.run();
    }

    public void flipCards() {
        int playerIndex = 1;
        for (Player player : players) {
            PlayingCard pc = player.getCard(0);
            pc.flip();
            viewable.showCardForPlayer(playerIndex++, player.getName(), pc.getRank().toString(), pc.getSuit().toString());
        }

        evaluateWinner();
        displayWinner();
        rebuildDeck();
        gameState = GameState.WinnerRevealed;
        this.run();
    }

    public void evaluateWinner() {
        winner = evaluator.evaluateWinner(players);
    }

    public void displayWinner() {
        viewable.showWinner(winner.getName());
    }

    public void rebuildDeck() {
        for (Player player : players) {
            deck.returnCardToDeck(player.removeCard());
        }
    }

    public void nextAction(String nextChoice) {
        if ("+q".equals(nextChoice)) {
            exitGame();
        } else {
            startGame();
        }
    }

    public void exitGame() {
        System.exit(0);
    }

}
