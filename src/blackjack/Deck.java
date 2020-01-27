package blackjack;

import java.util.ArrayList;

public class Deck {
    ArrayList<Card> cards = new ArrayList<Card>();

    public Deck(){
        this.reset();
    }

    private void reset(){
        this.cards.clear();
        int[] values = {1,2,3,4,5,6,7,8,9,10,11,12,13};
        String[] suits=  {"H", "D", "S", "C"};
        for (String suit: suits) {
            for (int val: values) {
                this.cards.add(new Card(val, suit));
            }
        }
    }

    public ArrayList<Card> cards() {
        return this.cards;
    }

}
