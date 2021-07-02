package com.openclassrooms.cardgame;

import com.openclassrooms.cardgame.controller.GameController;
import com.openclassrooms.cardgame.games.HighCardGamesEvaluatorImpl;
import com.openclassrooms.cardgame.view.GameSwingViewImpl;

import static com.openclassrooms.cardgame.DeckFactory.DeckType;
import static com.openclassrooms.cardgame.DeckFactory.makeDeck;

public class Games {
    public static void main(String[] args) {
        GameSwingViewImpl gsv = new GameSwingViewImpl();
        gsv.createAndShowGraphicalUserInterface();

        GameController gc = new GameController(makeDeck(DeckType.Normal), gsv, new HighCardGamesEvaluatorImpl());
        gc.run();
    }
}
