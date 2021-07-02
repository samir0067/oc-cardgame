package com.openclassrooms.cardgame.view;

import com.openclassrooms.cardgame.controller.GameController;

import javax.swing.*;
import java.awt.*;


public class GameSwingViewImpl implements GameViewable {

    GameController controller;
    JTextArea textArea;
    JFrame frame;

    public void createAndShowGraphicalUserInterface() {

        // Créer le cadre principal
        frame = new JFrame("MVC-SOLID-Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        // affichage vertical
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

        addControllerCommandTracker(contentPane);

        frame.setVisible(true);
    }

    // un endroit simple pour afficher ce que le contrôleur nous dit
    private void addControllerCommandTracker(Container contentPane) {
        textArea = new JTextArea("Game Status\n", 100, 1);
        JScrollPane scrollPane = new JScrollPane(textArea);
        addCenteredComponent(scrollPane, contentPane);
        textArea.setSize(500, 500);
    }

    // tous les contrôles sont ajoutés de manière à être centrés horizontalement dans la zone.
    private void addCenteredComponent(JComponent component, Container contentPane) {
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(component);
    }


    private void appendText(String text) {
        textArea.append(text + "\n");
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }


    @Override
    public void setController(GameController controller) {
        this.controller = controller;

    }

    @Override
    public void showPlayerName(int playerIndex, String playerName) {
        appendText("[" + playerIndex + "][" + playerName + "]");
    }

    @Override
    public void showCardForPlayer(int playerIndex, String playerName, String cardRank, String cardSuit) {
        appendText("[" + playerName + "][" + cardRank + ":" + cardSuit + "]");
    }

    @Override
    public void showWinner(String winnerName) {
        appendText("Winner!\n" + winnerName);
    }

    @Override
    public void showFaceDownCardForPlayer(int playerIndex, String name) {
        appendText("[" + name + "][][]");
    }

    @Override
    public void promptForPlayerName() {

        String result = (String) JOptionPane.showInputDialog(frame, "Add a player", "Player",
                JOptionPane.PLAIN_MESSAGE, null, null, "");

        if (result == null || result.isEmpty()) {
            controller.nextAction("+q");
        }

        controller.addPlayer(result);

        int addMore = JOptionPane.showConfirmDialog(frame, "Add more players ?", "More players", JOptionPane.YES_NO_OPTION);

        if (addMore == JOptionPane.NO_OPTION) {
            controller.startGame();
        }
    }

    @Override
    public void promptForFlip() {
        controller.flipCards();
    }

    @Override
    public void promptForNewGame() {
        int newGame = JOptionPane.showConfirmDialog(frame, "Play again ?", "Play again", JOptionPane.YES_NO_OPTION);
        controller.nextAction(newGame == JOptionPane.NO_OPTION ? "+q" : "");
    }
}
