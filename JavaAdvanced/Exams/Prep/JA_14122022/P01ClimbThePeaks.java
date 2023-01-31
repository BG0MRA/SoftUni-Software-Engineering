package Prep.JA_14122022;

import java.util.*;
import java.util.stream.LongStream;

public class P01ClimbThePeaks {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        String[] inputDailyFood = scanner.nextLine().split(",\\s+");
        String[] inputDailyStamina = scanner.nextLine().split(",\\s+");

        Deque<Integer> dailyFoodStack = new ArrayDeque<>();
        Deque<Integer> dailyStaminaQueue = new ArrayDeque<>();

        for (String dailyFood : inputDailyFood) {
            dailyFoodStack.push(Integer.parseInt(dailyFood));
        }
        for (String dailyStamina : inputDailyStamina) {
            dailyStaminaQueue.offer(Integer.parseInt(dailyStamina));
        }

        Map<String, Integer> allMountainPeaks = new LinkedHashMap<>();
        allMountainPeaks.put("Vihren", 80);
        allMountainPeaks.put("Kutelo", 90);
        allMountainPeaks.put("Banski Suhodol", 100);
        allMountainPeaks.put("Polezhan", 60);
        allMountainPeaks.put("Kamenitza", 70);

        Deque<String> alllPeaks = new ArrayDeque<>(Arrays.asList("Vihren", "Kutelo", "Banski Suhodol", "Polezhan", "Kamenitza"));

        List<String> conqueredPeaks = new LinkedList<>();


        while (!dailyFoodStack.isEmpty() && !dailyStaminaQueue.isEmpty() && !alllPeaks.isEmpty() ) {

           int dailyPower = dailyFoodStack.pop() + dailyStaminaQueue.poll();

            if (alllPeaks.size() != 0 ) {
                String dailyPeak = alllPeaks.getFirst();
                int peakDifficulty = allMountainPeaks.get(dailyPeak);

                if (dailyPower >= peakDifficulty) {
                    //save conquered Peak
                    conqueredPeaks.add(dailyPeak);
                    //remove the Peak from allPeaks Queue
                    alllPeaks.remove(dailyPeak);
                } else {
                }
            }

        }

        if (alllPeaks.isEmpty()) {
            System.out.println("Alex did it! He climbed all top five Pirin peaks in one week -> @FIVEinAWEEK");
        } else {
            System.out.println("Alex failed! He has to organize his journey better next time -> @PIRINWINS");
        }

        if (!conqueredPeaks.isEmpty()) {
            System.out.println("Conquered peaks: ");
            conqueredPeaks.forEach(System.out::println);
        }
    }
}
