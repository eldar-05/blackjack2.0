import java.util.ArrayList;
import java.util.List;

public class Player {
    String name;
    List<Card> hand = new ArrayList<>();
    int balance;

    public Player(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    public int calculateValue() {
        int value = 0;
        int aceCount = 0;

        for (Card card : hand) {
            value += card.value;
            if (card.rank.equals("Ace")) aceCount++;
        }

        while (value > 25 && aceCount > 0) {
            value -= 10;
            aceCount--;
        }

        return value;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public void resetHand() {
        hand.clear();
    }

    public String toString() {
        return hand.toString();
    }
}
