import blackjack.*;

import java.util.ArrayList;

public class main {
    public static void main(String[] args){
        Player random_player_1 = new RandomPlayer("Player One", 100.0);
        Player random_player_2 = new RandomPlayer("Player Two", 100.0);
        ArrayList<Player> players = new ArrayList<>();
        players.add(random_player_1);
        players.add(random_player_2);

        BlackJackTable table_1 = new BlackJackTable(1, 3, 3);
        table_1.sit(random_player_1, 0);
        table_1.displaySeats();

        Boolean change = true;
        while (change) {
            change = table_1.promptSeatChange();
            if (change) {
                table_1.changeSeats(players);
            } else if (!table_1.isReady()) {
                table_1.displayError();
                change = true;
            }
        }

    }
}