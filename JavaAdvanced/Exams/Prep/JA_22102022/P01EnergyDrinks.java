package Prep.JA_22102022;

import java.util.*;

public class P01EnergyDrinks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Stack
        Deque<Integer> milligramsCaffeine = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split(",\\s+"))
                .mapToInt(n -> Integer.parseInt(n))
                .forEach(e -> milligramsCaffeine.push(e));

        //Queue
        Deque<Integer> energyDrinks = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split(",\\s+"))
                .mapToInt(n -> Integer.parseInt(n))
                .forEach(e -> energyDrinks.offer(e));

        //34, 2, 3
        //40, 100, 250
        int maxCaffeinePerNight = 300;
        int currentCaffeine = 0;
        while (!milligramsCaffeine.isEmpty() && !energyDrinks.isEmpty()) {
            int milligramsCaffeineStack = milligramsCaffeine.pop();
            int energyDrinkQueue = 0;
            if (!energyDrinks.isEmpty()) {
                energyDrinkQueue = energyDrinks.poll();
            }
            int calcCaffeine = calculateCaffeine(milligramsCaffeineStack, energyDrinkQueue);

            if (calcCaffeine <= maxCaffeinePerNight) {
                currentCaffeine = calcCaffeine;
                maxCaffeinePerNight -= currentCaffeine;

            } else {
                energyDrinks.offer(energyDrinkQueue);
                maxCaffeinePerNight += 30;
                if (maxCaffeinePerNight >= 300) {
                    maxCaffeinePerNight = 300;
                }
                currentCaffeine -= 30;
                if (calcCaffeine <= 0) {
                    currentCaffeine = 0;
                }

            }

        }

        if (!energyDrinks.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Drinks left: ");
            while(!energyDrinks.isEmpty()) {
                Integer last = energyDrinks.getLast();
                Integer pop = energyDrinks.pop();
                if (!pop.equals(last)) {
                    sb.append(pop + ", ");
                } else {
                    sb.append(pop);
                }
            }
            System.out.println(sb);
        } else {
            System.out.println("At least Stamat wasn't exceeding the maximum caffeine.");
        }

        System.out.printf("Stamat is going to sleep with %d mg caffeine.", 300 - maxCaffeinePerNight);


    }

    public static int calculateCaffeine(int milligrams, int energyDrink) {
        int result = milligrams * energyDrink;

        return result;
    }
}