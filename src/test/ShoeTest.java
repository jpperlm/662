package test;

import blackjack.Card;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShoeTest {
    @Test
    public void testCountWorks() {
        Card c_1 = new  Card(1, "A", "Ace", "H", "Hearts");
        Card c_2 = new Card(7, "7", "Seven", "H", "Hearts");
        Card c_3 = new  Card(12, "12", "Queen", "H", "Hearts");

        assertEquals(c_1.getCountValue(), 1);
        assertEquals(c_2.getCountValue(), 0);
        assertEquals(c_3.getCountValue(), -1);
    }

}
