package com.openclassrooms.cardgame.model;

public class Player implements IPlayer{
    private final String name;
    private final Hand hand;

    public Player(String name) {
        super();
        this.name = name;
        hand = new Hand();
    }

    public void addCardToHand(PlayingCard pc) {
        hand.addCard(pc);
    }

    public PlayingCard getCard(int index) {
        return hand.getCard(index);
    }

    public PlayingCard removeCard() {
        return hand.removeCard();
    }

    public String getName() {
        return name;
    }
}
