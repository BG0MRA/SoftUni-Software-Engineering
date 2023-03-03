package E01Abstraction.P06_GreedyTimes;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long capacity = Long.parseLong(scanner.nextLine());
        String[] treasures = scanner.nextLine().split("\\s+");

        Bag bag = new Bag(capacity);

        for (int i = 0; i < treasures.length; i += 2) {
            String item = treasures[i];
            long quantity = Long.parseLong(treasures[i + 1]);
            bag.collectTreasure (item, quantity);
        }

        System.out.println(bag.info());
    }
}
