package E01Abstraction.P01_CardSuit;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        System.out.println(input+":");
//        for (int i = 0; i < CardSuits.values().length; i++) {
//            System.out.printf("Ordinal value: %d; Name value: %s\n", i, CardSuits.values()[i]);
//        }

        System.out.println(Arrays.stream(CardSuits.values())
                .map(v -> String.format("Ordinal value: %d; Name value: %s", v.ordinal(), v.name()))
                .collect(Collectors.joining(System.lineSeparator())));
    }
}
