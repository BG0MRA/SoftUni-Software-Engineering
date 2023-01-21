package E03SetsAndMaps;

import java.util.Collections;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class P03PeriodicTable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Set<String> chemicalElements = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            String[] compound = scanner.nextLine().split("\\s+");
            Collections.addAll(chemicalElements, compound);
        }

        System.out.println(String.join(" ", chemicalElements));

    }
}
