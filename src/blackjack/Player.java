package blackjack;

import java.util.ArrayList;

// Represents a Player
// Needs to be fleshed out more
public class Player {
    String name;
    Double money;
    History<Player, String> player_history = new History<>();

    public Player(String name, Double money){
        this.name = name;
        this.money = money;
    }
    public void makeBet(Double x) {
        this.money = this.money - x;
    }
    public void receiveWinning(Double x) {
        this.money = this.money + x;
    }
    public Integer getDecision(ArrayList<String> options) { return 0; }
    public String getName() {
        return this.name;
    }
    public Double getMoney() {
        return this.money;
    }
    public History<Player, String> getHistoryWrapper() { return this.player_history; }
    @Override
    public String toString() {
        return this.name;
    }

}
