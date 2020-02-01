package blackjack;

import java.util.ArrayList;

public abstract class Table {

    Integer maxSeats;
    Integer minSeats;
    ArrayList<Seat> seats = new ArrayList<Seat>();

    public abstract void sit(Player p, Integer i) throws MaxSeatExeception;
    public abstract void leave(Integer i);
    public abstract void play();
    public abstract void displaySeats();

}
