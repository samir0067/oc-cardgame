package com.openclassrooms.cardgame.view;

import com.openclassrooms.cardgame.controller.GameController;

import java.util.Scanner;

public class CommandLineView implements GameViewable {

    GameController controller;
    Scanner keyboard = new Scanner(System.in);

    public void setController(GameController gc) {
        this.controller = gc;
    }

    public void promptForPlayerName() {
        System.out.println("Entrez le nom du joueur: ");
        String name = keyboard.nextLine();
        if (name.isEmpty()) {
            controller.startGame();
        } else {
            controller.addPlayer(name);
        }
    }

    public void promptForFlip() {
        System.out.println("Appuyez sur la touche Entrée pour révéler les cartes");
        keyboard.nextLine();
        controller.flipCards();
    }

    public void promptForNewGame() {
        System.out.println("Appuyez sur la touche Entrée pour traiter à nouveau ou sur la touche +q pour quitter.");
        controller.nextAction(keyboard.nextLine());
    }

    public void showWinner(String playerName) {
        System.out.println("Le gagnant est " + playerName + " !");
    }

    public void showPlayerName(int playerIndex, String playerName) {
        System.out.println("[" + playerIndex + "][" + playerName + "]");
    }

    public void showFaceDownCardForPlayer(int i, String playerName) {
        System.out.println("[" + i + "][" + playerName + "][x][x]");
    }

    public void showCardForPlayer(int i, String playerName, String rank, String suit) {
        System.out.println("[" + i + "][" + playerName + "][" + rank + "][" + suit + "]");
    }
}
