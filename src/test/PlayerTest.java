package test;

import blackjack.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {
    @Test
    public void makeBetTest() {
        Player p = new Player("test", 100.0);
        assertEquals(p.getMoney(), 100.0);
        p.makeBet(10.0);
        assertEquals(p.getMoney(), 90.0);
    }
}
