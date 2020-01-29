package blackjack;

import java.util.ArrayList;
import java.util.Random;

public class UserPlayer extends Player {

    public UserPlayer(String name, Double money) {
        super(name, money);
    }
    public Integer getDecision(String prompt, ArrayList<String> options) {
       return new Random().nextInt(options.size());
    }

}
