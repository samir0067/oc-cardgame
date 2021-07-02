package com.openclassrooms.cardgame;

import com.openclassrooms.cardgame.DeckFactory.DeckType;
import com.openclassrooms.cardgame.controller.GameController;
import com.openclassrooms.cardgame.games.HighCardGameEvaluator;
import com.openclassrooms.cardgame.view.GameSwingView;

import static com.openclassrooms.cardgame.DeckFactory.makeDeck;

public class Games {
    public static void main(String[] args) {
        GameSwingView gsv = new GameSwingView();
        gsv.createAndShowGUI();

        GameController gc = new GameController(makeDeck(DeckType.Normal), gsv, new HighCardGameEvaluator());
        gc.run();
    }
}
