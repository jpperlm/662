package test;
import blackjack.Card;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class CardTest {
    @Test
    public void test() {
        assertEquals(1, new Card(1, "H").dummyTest());
    }
    @Test
    public void getCountValueTest() {
        Card c_1 = new Card(2, "H");
        Card c_2 = new Card(7, "H");
        Card c_3 = new Card(12, "H");

        assertEquals(c_1.getCountValue(), 1);
        assertEquals(c_2.getCountValue(), 0);
        assertEquals(c_3.getCountValue(), -1);
    }
    @Test
    public void faceValueTest() {
        Card c_1 = new Card(1, "H");
        Card c_2 = new Card(7, "H");
        Card c_3 = new Card(12, "H");

        assertEquals(c_1.faceValue(), "A");
        assertEquals(c_2.faceValue(), "7");
        assertEquals(c_3.faceValue(), "Q");
    }

    @Test
    public void fullSuit() {
        Card c_1 = new Card(1, "H");
        assertEquals(c_1.fullSuit(), "Hearts");
    }


}
