package test;

import blackjack.BlackJackTable;
import blackjack.Player;
import blackjack.Seat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlackJackTableTest {
    @Test
    public void sitPlayer() {
        BlackJackTable t = new BlackJackTable(1, 3, 3);
        Player p = new Player("test", 100.0);
        t.sit(p, 0);
        assertEquals(t.getSeats().get(0).getPlayer(), "test");
    }

    @Test
    public void removePlayer() {
        BlackJackTable t = new BlackJackTable(1, 3, 3);
        Player p = new Player("test", 100.0);
        t.sit(p, 0);
        t.leave(0);
        assertEquals(t.getSeats().get(0).getPlayer(), "Empty");
    }
}
