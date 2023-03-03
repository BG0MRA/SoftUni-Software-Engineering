package E01Abstraction.P02_CardRank;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        System.out.println(input + ":");

//        if (input.equals("Card Ranks")) {
//            for (int i = 0; i < CardRanks.values().length; i++) {
//                System.out.printf("Ordinal value: %d; Name value: %s\n", i, CardRanks.values()[i]);
//            }
//        }

        System.out.println(Arrays.stream(CardRanks.values())
                .map(v -> String.format("Ordinal value: %d; Name value: %s", v.ordinal(), v.name()))
                .collect(Collectors.joining(System.lineSeparator())));
    }
}
