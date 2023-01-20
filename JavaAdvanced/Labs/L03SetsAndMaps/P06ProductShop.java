package L03SetsAndMaps;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class P06ProductShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Map<String, Double>> foodShopsMap = new TreeMap<>();

        String input = scanner.nextLine();

        while (!input.equals("Revision")) {
            String[] tokens = input.split(", ");
            String shop = tokens[0];
            String product = tokens[1];
            double price = Double.parseDouble(tokens[2]);

            foodShopsMap.putIfAbsent(shop, new LinkedHashMap<>());
            Map<String, Double> productsMap = foodShopsMap.get(shop);
            productsMap.put(product, price);

            input = scanner.nextLine();
        }

        foodShopsMap.entrySet().forEach(entry -> {
            String shop = entry.getKey();
            System.out.println(shop + "->");

            Map<String, Double> productsMap = entry.getValue();

            productsMap.entrySet().forEach(product -> {
                String productName = product.getKey();
                Double price = product.getValue();
                System.out.printf("Product: %s, Price: %.1f\n", productName, price);
            });
        });

    }
}
