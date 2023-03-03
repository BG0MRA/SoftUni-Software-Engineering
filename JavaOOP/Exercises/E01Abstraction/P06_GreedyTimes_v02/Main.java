package E01Abstraction.P06_GreedyTimes_v02;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long capacity = Long.parseLong(scanner.nextLine());
        String[] safe = scanner.nextLine().split("\\s+");

        Bag bag = new Bag(capacity);

        for (int i = 0; i < safe.length; i += 2) {
            String item = safe[i];
            long quantity = Long.parseLong(safe[i + 1]);
            bag.collectTreasure (item, quantity);
        }

        System.out.print(bag.bagInfo());
    }
}
