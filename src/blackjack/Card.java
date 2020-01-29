package blackjack;

public class Card {
    Integer value;
    String short_value;
    String long_value;
    String short_suit;
    String long_suit;

    public Card(Integer v, String sv, String lv, String ss, String ls){
        this.value = v;
        this.short_value = sv;
        this.long_value = lv;
        this.short_suit = ss;
        this.long_suit = ls;
    }


    public String getSuit() {
        return this.short_suit;
    }
    public String fullSuit() {
        return this.long_suit;
    }

    public Integer getValue() {
        return this.value;
    }

    public Integer getCountValue() {
        if (this.value >= 2 && this.value <= 6 ) {
            return 1;
        } else if (this.value >= 10 || this.value.equals(1)) {
            return -1;
        }
        return 0;
    }

    public String faceValue() {
        return this.short_value;
    }
    public String faceLongValue() {
        return this.long_value;
    }


    public Integer dummyTest() {
        return 1;
    }

    @Override
    public String toString() {
        return this.faceValue() + " " + this.fullSuit();
    }

}
