import org.junit.Assert;

import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BankSystemTest {

    @org.junit.jupiter.api.Test
    void transfer() {
        BankSystem bank = new BankSystem(30);
        long expected = bank.countAllCardsValues();
        Map<String, Integer> cards = bank.getAllCards();
        Random random = new Random();
        for(int i = 0; i < 1000; i++) {
            String randomFrom = (String) cards.keySet().toArray()[random.nextInt(cards.size())];
            String randomTo = (String) cards.keySet().toArray()[random.nextInt(cards.size())];
            Thread t = new Thread(String.valueOf(bank.transfer(randomFrom, randomTo, random.nextInt(1000))));
            t.start();
        }
        long actual = bank.countAllCardsValues();
        Assert.assertEquals(expected,actual);
    }
}