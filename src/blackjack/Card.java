package blackjack;

public class Card {
    Integer value;
    String suit;

    public Card(Integer v, String s){
        this.setValue(v);
        this.setSuit(s);
    }

    public void setValue(Integer v) {
        this.value = v;
    }

    public void setSuit(String s) {
        this.suit = s;
    }

    public String getSuit() {
        return this.suit;
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
        switch (this.value) {
            case 1:
                return "A";
            case 2:
                return "2";
            case 3:
                return "3";
            case 4:
                return "4";
            case 5:
                return "5";
            case 6:
                return "6";
            case 7:
                return "7";
            case 8:
                return "8";
            case 9:
                return "9";
            case 10:
                return "10";
            case 11:
                return "J";
            case 12:
                return "Q";
            case 13:
                return "K";
            default:
                return "";
        }
    }

    public String fullSuit() {
        switch (this.suit) {
            case "H": return "Hearts";
            case "D": return "Diamonds";
            case "S": return "Spades";
            case "C": return "Clubs";
            default: return "";
        }
    }
    public Integer dummyTest() {
        return 1;
    }

    @Override
    public String toString() {
        return this.faceValue() + " " + this.fullSuit();
    }

}
