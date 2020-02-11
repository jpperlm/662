package blackjack;

import java.util.ArrayList;
import java.util.Collections;

// Represents a Shoe (a blackjack term)
// A shoe is a shuffled set of decks, typically 1-8 decks
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

    // Setter
    public void setCards(ArrayList<Card> c) {
        this.cards = c;
    }

    // Getters
    public ArrayList<Card> cards() {
        return this.cards;
    }

    // Gets the current Count of the shoe
    // The count is determined by the count values of the cards which have been played
    // Played cards are the cards in the array before the currentCard flag
    public int count() {
        int count = 0;
        for (int i = 0; i < this.currentCard; ++i) {
            count += this.cards.get(i).getCountValue();
        }
        return count;
    }

    // If a shoe has less than half cards remaining, it is not playable
    public Boolean isPlayable() {
        return this.currentCard < this.cards.size() / 2;
    }
    //    Actions

    // Deals a card to the Seat passed thru the params
    public void dealCard(Seat s) {
        s.addCard(cards.get(this.currentCard));
        this.currentCard++;
    }

    public void display() {
        for (Card c: this.cards) {
            System.out.println(c);
        }
    }

    // Shuffles the entire shoe (cards)
    private void shuffle() {
        Collections.shuffle(this.cards);
        this.currentCard = 0;
    }

    // Resets the shoe
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


}
