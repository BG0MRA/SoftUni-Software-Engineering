package E03SetsAndMaps;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class P07HandsOfCards {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();
        Map<String, HashSet<String>> playersMap = new LinkedHashMap<>();

        while (!command.equals("JOKER")) {
            String[] tokens = command.split(": ");
            String name = tokens[0];
            String[] cards = tokens[1].split(", ");

            playersMap.putIfAbsent(name, new HashSet<>());

            for (String card : cards) {
                playersMap.get(name).add(card);
            }

            command = scanner.nextLine();
        }

        for (var player : playersMap.entrySet()) {
            String playerName = player.getKey();
            int points = calculatePoints(player.getValue());
            System.out.println(playerName + ": " + points);
        }
    }

    private static int calculatePoints(HashSet<String> cards) {
        int points = 0;
        for (String card : cards) {
            String cardPower = card.substring(0, card.length() - 1);
            char cardType = card.charAt(card.length() - 1);
            int cardPoints = getPower(cardPower) * getType(cardType);
            points += cardPoints;
        }
        return points;
    }

    private static int getPower(String power) {
        switch (power) {
            case "J":
                return 11;
            case "Q":
                return 12;
            case "K":
                return 13;
            case "A":
                return 14;
            default:
                return Integer.parseInt(power);
        }
    }

    private static int getType(char type) {
        int typePoints = 0;
        switch (type) {
            case 'S':
                typePoints = 4;
                break;
            case 'H':
                typePoints = 3;
                break;
            case 'D':
                typePoints = 2;
                break;
            case 'C':
                typePoints = 1;
                break;
        }
        return typePoints;

    }

}
