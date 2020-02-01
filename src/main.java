import blackjack.*;

import java.util.ArrayList;

public class main {
    public static void main(String[] args){
        run_table();

    }

    private static void run_table() {
        Player random_player_1 = new RandomPlayer("Random P1", 100.0);
        Player random_player_2 = new RandomPlayer("Random P2", 100.0);
        Player user_player = new RandomPlayer("User (you)", 100.0);

        ArrayList<Player> players = new ArrayList<>();
        players.add(random_player_1);
        players.add(random_player_2);
        players.add(user_player);

        BlackJackTable table_1 = new BlackJackTable(1, 3, 3);
        try {
            table_1.sit(user_player, 0);
        }
        catch (MaxSeatExeception e) {
            System.out.println(e);
        }

        table_1.displaySeats();
        Boolean change = true;
        while (change) {
            change = table_1.promptSeatChange();
            if (change) {
                try {
                    table_1.changeSeats(players);
                }
                catch (MaxSeatExeception e) {
                    System.out.println(e);
                }
            } else if (!table_1.isReady()) {
                table_1.displayError();
                change = true;
            }
        }
        table_1.play();
    }

    private static void testing() {
        Shoe shoe = new Shoe(3);
        Seat seat = new Seat();
        System.out.println(shoe.count());
        shoe.dealCard(seat);
        System.out.println(shoe.count());
        shoe.dealCard(seat);
        System.out.println(shoe.count());
    }
}