package E08IteratorsAndComparators.P01ListyIterator;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListyIterator listyIterator = null;

        String input = scanner.nextLine();
        while (!input.equals("END")) {
            String[] commandparts = input.split("\\s+");
            String command = commandparts[0];
            switch (command) {
                case "Create":
                    if (commandparts.length > 1) {
                        String[] elements = Arrays.copyOfRange(commandparts, 1, commandparts.length);
                        listyIterator = new ListyIterator(elements);
                    } else {
                        listyIterator = new ListyIterator();
                    }

                    break;
                case "Move":
                    System.out.println(listyIterator.move());
                    break;
                case "Print":
                    try {
                        listyIterator.print();
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "HasNext":
                    System.out.println(listyIterator.hasNext());
                    break;
            }

            input = scanner.nextLine();
        }

    }
}
