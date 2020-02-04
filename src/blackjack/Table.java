package blackjack;

import java.util.ArrayList;

// Abstract Table Class
// Under Refactor as I am refactoring BlackJackTable as well
public abstract class Table {
    Integer maxSeats;
    Integer minSeats;
    ArrayList<Seat> seats = new ArrayList<Seat>();
    public abstract void loop(ArrayList<Player> potential_players);

}
