package E02Encapsulation.P03_ShoppingSpree;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] peopleData = scanner.nextLine().split(";");
        Map<String, Person> people = new LinkedHashMap<>();
        Map<String, Product> products = new LinkedHashMap<>();

        for (String personString : peopleData) {
            String[] personData = personString.split("=");
            String name = personData[0];
            double money = Double.parseDouble(personData[1]);

            Optional<Person> person = Optional.empty();

            try {
                person = Optional.of(new Person(name, money));
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
                return;
            }

            person.ifPresent(p -> people.put(p.getName(), p));

        }

        String[] productsData = scanner.nextLine().split(";");

        for (String productString : productsData) {
            String[] productData = productString.split("=");

            String name = productData[0];
            double cost = Double.parseDouble(productData[1]);

            Optional<Product> product = Optional.empty();

            try {
                product = Optional.of(new Product(name, cost));
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
                return;
            }

            product.ifPresent(p -> products.put(p.getName(), p));

        }

        String input = scanner.nextLine();

        while (!input.equals("END")) {
            String names[] = input.split("\\s+");
            String personName = names[0];
            String productName = names[1];

            Person buyer = people.get(personName);
            Product product = products.get(productName);

            try {
                buyer.buyProduct(product);
                System.out.printf("%s bought %s\n", buyer.getName(), product.getName());
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }

            input = scanner.nextLine();
        }

        System.out.println(people.values().stream()
                .map(Person::toString)
                .collect(Collectors.joining(System.lineSeparator())));

    }
}
