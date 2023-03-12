package E04InterfacesAndAbstraction.P04_FoodShortage;


import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numberOfPeople = Integer.parseInt(scanner.nextLine());

        Map<String, Buyer> buyers = new LinkedHashMap<>();

        while (numberOfPeople-- > 0) {
            String[] personData = scanner.nextLine().split("\\s+");
            String name = personData[0];
            int age = Integer.parseInt(personData[1]);

            Buyer buyer;

            if (personData.length == 4) {
                String id = personData[2];
                String birthDate = personData[3];
                buyer = new Citizen(name, age, id, birthDate);

            } else {
                String group = personData[2];
                buyer = new Rebel(name, age, group);
            }

            buyers.putIfAbsent(name, buyer);

        }

        String input = scanner.nextLine();

        while (!"End".equals(input)) {
            Buyer buyer = buyers.get(input);

            if (buyer != null) {
                buyer.buyFood();
            }

            input = scanner.nextLine();
        }

        int totalFood = buyers.values().stream()
                .mapToInt(Buyer::getFood)
                .sum();

        System.out.println(totalFood);

    }
}



