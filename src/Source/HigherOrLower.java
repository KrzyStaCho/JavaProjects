package Source;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class HigherOrLower {

    public static void run() {
        HigherOrLower.main(null);
    }

    public static void main(String[] args) {

        ArrayList<Card> deck = Card.ShuffleDeck(Card.GetFullDeck());
        Card card = deck.get(deck.size()-1);

        ShowCard(card);
        deck.remove(deck.size()-1);

        boolean status = true;
        int counter=0;
        while(status) {

            if(deck.isEmpty()) {
                System.out.println("Talia skonczona wygrałeś grę");
                System.out.println("Zgadłeś " + counter + "razy!");
            }

            HighLow userInput = GetUserChoice();
            Card nextCard = deck.get(deck.size()-1);

            if(card.GetIntValue() < nextCard.GetIntValue()) {
                if(ShowResult(userInput, HighLow.HIGH)) {
                    ShowCard(nextCard);
                    counter++;
                } else {
                    System.out.println("Zgadłeś " + counter + "razy!");
                    ShowCard(nextCard);
                    break;
                }
            } else if(card.GetIntValue() > nextCard.GetIntValue()) {
                if(ShowResult(userInput, HighLow.LOW)) {
                    ShowCard(nextCard);
                    counter++;
                } else {
                    System.out.println("Zgadłeś " + counter + "razy!");
                    ShowCard(nextCard);
                    break;
                }
            } else {
                ShowCard(nextCard);
                counter++;
            }

            deck.remove(deck.size()-1);
            card = nextCard;
        }
    }

    public static boolean ShowResult(HighLow userInput, HighLow target) {
        if(userInput==target) {
            System.out.println("Brawo!");
            return true;
        } else {
            System.out.println("Przegrałes!");
            return false;
        }
    }

    public static void ShowCard(Card card) {
        System.out.println("------------------");
        System.out.print("Karta to: ");
        System.out.println(card.getValue() + " " + card.getType());
        System.out.println("------------------");
    }

    public static HighLow GetUserChoice() {
        Scanner userInput = new Scanner(System.in);

        System.out.println("----------------------");
        System.out.println("Następna karta będzie wyżej{H} czy niżej{L}?");
        System.out.print("[H]/[L]: ");
        String userChoice = userInput.next();
        System.out.println("----------------------");

        HighLow choice = null;

        while(choice==null) {
            if(userChoice.equals("H") || userChoice.equals("h")) {
                choice = HighLow.HIGH;
            } else if(userChoice.equals("L") || userChoice.equals("l")) {
                choice = HighLow.LOW;
            } else {
                System.out.println("Nieprawidłowa wartość, prosze podac 'H' lub 'L'");
            }
        }

        return choice;
    }
}

class Card {
    private TypeOfCard type;
    private ValueOfCard value;

    public static final TypeOfCard[] CARD_TYPES = {TypeOfCard.PIK, TypeOfCard.TREFL, TypeOfCard.KARO, TypeOfCard.KIER};
    public static final ValueOfCard[] CARD_VALUES =
            { ValueOfCard.TWO, ValueOfCard.THREE, ValueOfCard.FOUR, ValueOfCard.FIVE, ValueOfCard.SIX,
            ValueOfCard.SEVEN, ValueOfCard.EIGHT, ValueOfCard.NINE, ValueOfCard.TEN, ValueOfCard.ELEVEN,
            ValueOfCard.AS, ValueOfCard.WALET, ValueOfCard.DAMA, ValueOfCard.KROL };

    public Card(TypeOfCard type, ValueOfCard value) {
        this.type = type;
        this.value = value;
    }

    public static ArrayList<Card> GetFullDeck() {

        ArrayList<Card> deck = new ArrayList<>();

        for(TypeOfCard type : CARD_TYPES) {
            for(ValueOfCard value : CARD_VALUES) {
                deck.add(new Card(type, value));
            }
        }

        return deck;
    }

    public static ArrayList<Card> ShuffleDeck(ArrayList<Card> deck) {
        Collections.shuffle(deck);
        return deck;
    }

    public int GetIntValue() {
        return value.getValue();
    }

    public TypeOfCard getType() {
        return type;
    }

    public ValueOfCard getValue() {
        return value;
    }
}

enum TypeOfCard {
    TREFL("Trefl"), KARO("Karo"),
    KIER("Kier"), PIK("Pik");

    private String name;

    TypeOfCard(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

enum ValueOfCard {
    TWO("Dwa", 2),
    THREE("Trzy", 3),
    FOUR("Cztery", 4),
    FIVE("Pięć", 5),
    SIX("Sześć", 6),
    SEVEN("Siedem", 7),
    EIGHT("Osiem", 8),
    NINE("Dziewięć", 9),
    TEN("Dziesięć", 10),
    ELEVEN("Jedenaście", 11),
    AS("As", 1),
    WALET("Walet", 12),
    DAMA("Dama", 13),
    KROL("Król", 14);

    private int value;
    private String name;

    ValueOfCard(String name, int value) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

enum HighLow {
    HIGH, LOW
}
