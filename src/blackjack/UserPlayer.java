package blackjack;

import java.util.ArrayList;
import java.util.Random;

// Represents a User who will be able to give input
// Currently not setup, copied from Random Player
public class UserPlayer extends Player implements Visualize {

    public UserPlayer(String name, Double money) {
        super(name, money);
        this.player_history.add(this);
    }

    @Override
    public Integer getDecision(ArrayList<String> options) {
        return this.waitResponse(options);
    }

}
