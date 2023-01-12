package E01StacksAndQueues;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class P09PoisonousPlants {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int initialNumberOfPlants = Integer.parseInt(scanner.nextLine());
        int[] initialPesticides = Arrays.stream(scanner.nextLine()
                .split(" ")).mapToInt(Integer::parseInt).toArray();
        ArrayDeque<Integer> queueOfPesticides = new ArrayDeque<>();

        for (int i = initialPesticides.length - 1; i >= 0; i--) {
            queueOfPesticides.offer(initialPesticides[i]);
        }
        boolean isAllPlantSurvive = false;
        int pastDays = 0;

        while (!isAllPlantSurvive) {

            int numberOfPlants = queueOfPesticides.size();
            for (int i = 1; i < numberOfPlants; i++) {
                int currentPlanPesticides = queueOfPesticides.poll();
                int leftPlantPesticides = queueOfPesticides.peek();
                if (currentPlanPesticides <= leftPlantPesticides) {
                    queueOfPesticides.offer(currentPlanPesticides);
                }
            }
            queueOfPesticides.offer(queueOfPesticides.poll());
            if (numberOfPlants == queueOfPesticides.size()) {
                isAllPlantSurvive = true;
                break;
            }
            pastDays++;
        }

        System.out.println(pastDays);

    }
}
