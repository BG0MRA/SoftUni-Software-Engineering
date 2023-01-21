package E03SetsAndMaps;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class P04CountSymbols {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();
        Map<Character, Integer> map = new TreeMap<>();

        for (char symbol : text.toCharArray()) {
            map.putIfAbsent(symbol, 0);
            int count = map.get(symbol) + 1;
            map.put(symbol, count);
        }

        map.entrySet().forEach(entry -> {
            System.out.printf("%c: %d time/s\n", entry.getKey(), entry.getValue());
        });
    }
}
