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

    // Setter
    public void setCards(ArrayList<Card> c) {
        this.cards = c;
    }

    // Getters
    public ArrayList<Card> cards() {
        return this.cards;
    }

    public int count() {
        int count = 0;
        for (int i = 0; i < this.currentCard; ++i) {
            count += this.cards.get(i).getCountValue();
        }
        return count;
    }

    public Boolean isPlayable() {
        return this.currentCard < this.cards.size() / 2;
    }


    //    Actions
    public void dealCard(Seat s) {
        s.addCard(cards.get(this.currentCard));
//        System.out.println("Dealing Card " + cards.get(this.currentCard) );
        this.currentCard++;
    }

    public void display() {
        for (Card c: this.cards) {
            System.out.println(c);
        }
    }

    private void shuffle() {
        Collections.shuffle(this.cards);
        this.currentCard = 0;
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


}
