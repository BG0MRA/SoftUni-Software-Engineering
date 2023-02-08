package E08IteratorsAndComparators.P03StackIterator;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomStack customStack = new CustomStack();

        String input = scanner.nextLine();
        while (!input.equals("END")) {
            if (input.contains("Push")) {
                int[] elements = Arrays.stream(input.replace("Push ", "")
                                .split(", "))
                        .mapToInt(Integer::parseInt)
                        .toArray();

                Arrays.stream(elements).forEach(customStack::push);

            } else if (input.equals("Pop")) {
                try {
                    customStack.pop();
                } catch (IllegalStateException e) {
                    System.out.println(e.getMessage());
                }

            }

            input = scanner.nextLine();
        }

        try {
            customStack.forEach(System.out::println);
            customStack.forEach(System.out::println);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

    }
}