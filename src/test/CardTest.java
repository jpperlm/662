package test;
import blackjack.Card;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class CardTest {
    @Test
    public void test() {
        assertEquals(1, new Card(1, "A", "Ace", "H", "Hearts").dummyTest());
    }
    @Test
    public void getCountValueTest() {
        Card c_1 = new  Card(5, "5", "Five", "H", "Hearts");
        Card c_2 = new Card(7, "7", "Seven", "H", "Hearts");
        Card c_3 = new  Card(12, "12", "Queen", "H", "Hearts");

        assertEquals(c_1.getCountValue(), 1);
        assertEquals(c_2.getCountValue(), 0);
        assertEquals(c_3.getCountValue(), -1);
    }
    @Test
    public void faceValueTest() {
        Card c_1 = new  Card(1, "A", "Ace", "H", "Hearts");
        Card c_2 = new Card(7, "7", "Seven", "H", "Hearts");
        Card c_3 = new  Card(12, "Q", "Queen", "H", "Hearts");

        assertEquals(c_1.faceValue(), "A");
        assertEquals(c_2.faceValue(), "7");
        assertEquals(c_3.faceValue(), "Q");
    }

    @Test
    public void fullSuit() {
        Card c_1 = new  Card(1, "A", "Ace", "H", "Hearts");
        assertEquals(c_1.fullSuit(), "Hearts");
    }


}
