package E03SetsAndMaps;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class P02SetsOfElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] setsLength = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int firstSetLength = setsLength[0];
        int secondSetLength = setsLength[1];

        Set<Integer> firstSet = new LinkedHashSet<>();

        for (int i = 0; i < firstSetLength; i++) {
            int number = Integer.parseInt(scanner.nextLine());
            firstSet.add(number);
        }

        Set<Integer> secondSet = new LinkedHashSet<>();
        for (int i = 0; i < secondSetLength; i++) {
            int number = Integer.parseInt(scanner.nextLine());
            secondSet.add(number);
        }

        Set<Integer> uniqueNumbers = new LinkedHashSet<>();

        uniqueNumbers.addAll(firstSet);
        uniqueNumbers.addAll(secondSet);
        uniqueNumbers.removeIf
                (number -> !firstSet.contains(number) || !secondSet.contains(number));

        uniqueNumbers.forEach(n -> System.out.print(n + " "));
    }
}
