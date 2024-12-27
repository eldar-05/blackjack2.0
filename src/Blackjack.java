import java.util.Scanner;

public class Blackjack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deck deck = new Deck();

        System.out.println("\n");
        System.out.println("How much money do you have? Minimum money 10 USD. 1 game point = 1 USD");
        System.out.print("> ");
        Player player = new Player("Eldar", scanner.nextInt());
        Player dealer = new Player("Dealer", 999999999);

        System.out.println();
        System.out.println("                  >> Welcome to BlackJack25! Target: 25 <<");

        while (player.balance >= 10) {
            System.out.println("\nYour balance: " + player.balance);
            System.out.print("Enter your bet (minimum 10): > ");
            int bet = scanner.nextInt();

            if (bet < 10 || bet > player.balance) {
                System.out.println("Not enough money");
                continue;
            }

            player.resetHand();
            dealer.resetHand();

            player.addCard(deck.takeCard());
            player.addCard(deck.takeCard());
            dealer.addCard(deck.takeCard());

            System.out.println("Your hand: " + player + " " + player.calculateValue());
            System.out.println("Dealer's card: " + dealer.hand.get(0));

            boolean playerLose = false;
            while (true) {
                System.out.print("Hit or Stand? (h/s): >>> ");
                String choice = scanner.next();
                if (choice.equalsIgnoreCase("h")) {
                    player.addCard(deck.takeCard());
                    System.out.println("Your hand: " + player + " " + player.calculateValue());
                    if (player.calculateValue() > 25) {
                        System.out.println("You lose this game!");
                        playerLose = true;
                        break;
                    }
                } else if (choice.equalsIgnoreCase("s")) {
                    break;
                } else {
                    System.out.println("Try again");
                }
            }

            if (!playerLose) {
                while (dealer.calculateValue() < 17) {
                    dealer.addCard(deck.takeCard());
                }
                System.out.println("Dealer's hand: " + dealer);

                int playerValue = player.calculateValue();
                int dealerValue = dealer.calculateValue();

                if (dealerValue > 25 || playerValue > dealerValue) {
                    System.out.println("You win!");
                    player.balance += bet * 1.5;
                } else if (dealerValue > playerValue) {
                    System.out.println("Dealer wins!");
                    player.balance -= bet;
                } else {
                    System.out.println("Its a tie!");
                }
            }

            System.out.print("Play again? (yes/no): ");
            if (!scanner.next().equalsIgnoreCase("yes")) {
                break;
            }
        }

        
        if(player.balance > 10 ){
            System.out.println("You finished game. You can convert " + player.balance + "$ USD to your mBank");
        }
        else{
            System.out.println("You dont have enough money to play. You can convert " + player.balance + "$ USD to your mBank");
        }

    }
}
