package blackjack;

// Represents a playing card
// The card is created to accept values typically used for a deck of 52 playing cards but
//  is left abstract enough that it could be used for other games as the code expands
//  (potentially more games than just blackjack that use non standard cards)
public class Card {
    Integer value; // The value of the card, typically 1-13
    String short_value; // The abbreviation for the value, typically 1=>A, 2=>2, ... 13=>K
    String long_value; // The full name for the value, 1=>Ace, 2=>Two, 13=>King
    String short_suit; // The short suit H, S, C, D
    String long_suit; // The long suit, Hearts, Spades, Clubs, Diamonds

    public Card(Integer v, String sv, String lv, String ss, String ls){
        this.value = v;
        this.short_value = sv;
        this.long_value = lv;
        this.short_suit = ss;
        this.long_suit = ls;
    }

    // Getters
    public String getSuit() {
        return this.short_suit;
    }

    public String fullSuit() {
        return this.long_suit;
    }

    public Integer getValue() {
        return this.value;
    }

    public String faceValue() {
        return this.short_value;
    }

    public String faceLongValue() {
        return this.long_value;
    }

    // Gets the 'Count' of the card in reference to card counting
    // 2-6 = 1
    // 10-K & A = -1
    // Others = 0
    public Integer getCountValue() {
        if (this.value >= 2 && this.value <= 6 ) {
            return 1;
        } else if (this.value >= 10 || this.value.equals(1)) {
            return -1;
        }
        return 0;
    }
    @Override
    public String toString() {
        return this.faceValue() + " " + this.fullSuit();
    }

}
