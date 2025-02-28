import java.util.*;

class Card {
    String symbol;
    String value;

    public Card(String symbol, String value) {
        this.symbol = symbol;
        this.value = value;
    }

    @Override
    public String toString() {
        return value + " of " + symbol;
    }
}

public class CardCollection {
    private List<Card> cards = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public void addCard() {
        System.out.print("Enter card symbol (Hearts, Spades, Diamonds, Clubs): ");
        String symbol = sc.nextLine();
        System.out.print("Enter card value (e.g., Ace, King, 10, etc.): ");
        String value = sc.nextLine();
        cards.add(new Card(symbol, value));
        System.out.println("Card added successfully!");
    }

    public void searchBySymbol() {
        System.out.print("Enter symbol to search: ");
        String searchSymbol = sc.nextLine();
        boolean found = false;
        for (Card card : cards) {
            if (card.symbol.equalsIgnoreCase(searchSymbol)) {
                System.out.println(card);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No cards found for symbol: " + searchSymbol);
        }
    }

    public void displayAllCards() {
        if (cards.isEmpty()) {
            System.out.println("No cards in the collection.");
            return;
        }
        System.out.println("Card Collection:");
        for (Card card : cards) {
            System.out.println(card);
        }
    }

    public void start() {
        while (true) {
            System.out.println("\n1. Add Card");
            System.out.println("2. Search Cards by Symbol");
            System.out.println("3. Display All Cards");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addCard();
                case 2 -> searchBySymbol();
                case 3 -> displayAllCards();
                case 4 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    public static void main(String[] args) {
        CardCollection collection = new CardCollection();
        collection.start();
    }
}
