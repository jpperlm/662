package blackjack;

import java.util.ArrayList;

public class Seat {
    Player player;
    Boolean occupied = false;
    Boolean dealer = false;
    ArrayList<Card> cards = new ArrayList<Card>();

    public Seat() {

    }

    public void addPlayer(Player p) {
        this.player = p;
        this.occupied = true;
    }
    public void removePlayer() {
        this.player = null;
        this.occupied = false;
    }

    public void clearCards() {
        this.cards.clear();
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


}
