package blackjack;

import java.util.ArrayList;

// Represents a Seat (at a table)
// Seats have cards that can be given to them
// Seats can be occupied or empty
public class Seat {
    Player player;
    Boolean occupied = false;
    Boolean dealer = false;
    ArrayList<Card> cards = new ArrayList<Card>();

    public Seat() {}

    // Adds a player to the seat
    // Sets the occupied flag to true
    public void addPlayer(Player p) {
        this.player = p;
        this.occupied = true;
    }

    // Removes a player from the seat
    public void removePlayer() {
        this.player = null;
        this.occupied = false;
    }

    // Clears the cards array, equivalent of clearing the seat
    public void clearCards() {
        this.cards.clear();
    }

    public void addCard(Card c) {
        this.cards.add(c);
    }

    public Card getCard(Integer i) {
        if (this.cards.size() > i) {
            return this.cards.get(i);
        }
        return null;
    }

    public void makeDealer() {
        this.dealer = true;
    }

    public String getPlayer() {
        if (this.occupied) {
            return this.player.getName();
        } else {
            return "Empty";
        }
    }

    public Player getPlayerObject() {
        return this.player;
    }



}
