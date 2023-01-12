package E01StacksAndQueues;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class P04BasicQueueOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] integers = scanner.nextLine().split(" ");
        int countToOffer = Integer.parseInt(integers[0]); //N
        int countToPoll = Integer.parseInt(integers[1]); //S
        int elementToSearch = Integer.parseInt(integers[2]); //X
        int[] numbers = Arrays.stream(scanner.nextLine()
                        .split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < countToOffer; i++) {
            queue.offer(numbers[i]);
        }

        for (int i = 0; i < countToPoll; i++) {
            queue.poll();
        }

        if (queue.isEmpty()) {
            System.out.println(0);
        } else if (queue.contains(elementToSearch)) {
            System.out.println("true");
        } else {
            System.out.println(Collections.min(queue));
            // System.out.println(queue.stream().mapToInt(e -> e).min().getAsInt());
        }

    }
}
