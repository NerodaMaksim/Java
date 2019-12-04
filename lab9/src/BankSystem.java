import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BankSystem {
    private HashMap<String, Integer> allCards;

    BankSystem(int numberOfCards) {
        Random random = new Random();
        allCards = new HashMap<>();
        for (int i = 0; i < numberOfCards; i++) {
            String cardId = String.valueOf(random.nextInt(10)) + String.valueOf(random.nextInt(10)) + String.valueOf(random.nextInt(10)) + String.valueOf(random.nextInt(10)) + String.valueOf(random.nextInt(10)) + String.valueOf(random.nextInt(10)) + String.valueOf(random.nextInt(10)) + String.valueOf(random.nextInt(10)) + String.valueOf(random.nextInt(10)) + String.valueOf(random.nextInt(10)) + String.valueOf(random.nextInt(10)) + String.valueOf(random.nextInt(10));
            Integer cardValue = random.nextInt(1000000);
            allCards.put(cardId, cardValue);
        }
    }

    HashMap<String, Integer> getAllCards() {
        return allCards;
    }

    long countAllCardsValues() {
        long sum = 0;
        for (Map.Entry<String, Integer> card : allCards.entrySet()) {
            sum += card.getValue();
        }
        return sum;
    }

    synchronized boolean transfer(String from, String to, int amount) {
        try {
            if(allCards.containsKey(from) && allCards.get(from) - amount >= 0)
                allCards.put(from, allCards.get(from) - amount);
            else
                throw new Exception();
        } catch (Exception ex) {
            System.out.println("Card does't exist or not enough money on: " + from);
            return false;
        }
        try {
            if(allCards.containsKey(to))
                allCards.put(to, allCards.get(to) + amount);
            else
                throw new Exception();
        } catch (Exception ex) {
            System.out.println("Card does't exist");
            allCards.put(from, allCards.get(from) + amount);
            return false;
        }
        return true;
    }
}
