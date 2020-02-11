import blackjack.*;

import java.util.ArrayList;

public class main {
    public static void main(String[] args){
        run_table();
    }

    private static void run_table() {
        Player random_player_1 = new RandomPlayer("Random P1", 100.0);
        Player random_player_2 = new RandomPlayer("Random P2", 100.0);
        Player user_player = new UserPlayer("Jason (you)", 100.0);

        ArrayList<Player> players = new ArrayList<>();
        players.add(random_player_1);
        players.add(random_player_2);
        players.add(user_player);

        BlackJackTable table_1 = new BlackJackTable(1, 3, 3);
        table_1.loop(players);
    }

}