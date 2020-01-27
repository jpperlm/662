package blackjack;

import java.util.ArrayList;
import java.util.Collections;

public class Shoe {
    Integer numDecks;
    Integer currentCard = 0;
    ArrayList<Deck> decks = new ArrayList<Deck>();
    ArrayList<Card> cards = new ArrayList<Card>();

    public Shoe( Integer decks ){
        this.numDecks = decks;
        this.resetDecks();
        this.shuffle();
    }

    private void resetDecks(){
        this.decks.clear();
        this.cards.clear();
        this.currentCard = 0;
        for(int i = 0; i < this.numDecks; ++i) {
            this.decks.add(new Deck());
        }
        for (Deck d: this.decks) {
            this.cards.addAll(d.cards());
        }
    }

    private void shuffle() {
        Collections.shuffle(this.cards);
        this.currentCard = 0;
    }

    public ArrayList<Card> cards() {
        return this.cards;
    }

    public void display() {
        for (Card c: this.cards) {
            System.out.println(c);
        }
    }


}