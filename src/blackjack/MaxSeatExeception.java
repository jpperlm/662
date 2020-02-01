package blackjack;

public class MaxSeatExeception extends Exception {
    int max;
    MaxSeatExeception(int num) {
        max = num;
    }
    public String toString() {
        return ("MaxSeatException: Players can only occupy a maximum of " + this.max + " seats");
    }
}
