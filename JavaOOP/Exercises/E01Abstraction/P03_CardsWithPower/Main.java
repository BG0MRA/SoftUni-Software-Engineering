package E01Abstraction.P03_CardsWithPower;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CardRanks cardRank = CardRanks.valueOf(scanner.nextLine());
        CardSuits cardSuit = CardSuits.valueOf(scanner.nextLine());

        Card card = new Card(cardRank, cardSuit);

        System.out.println(card.cardInfo());

    }
}
