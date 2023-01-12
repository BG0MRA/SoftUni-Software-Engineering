package E01StacksAndQueues;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class P02BasicStackOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] integers = scanner.nextLine().split(" ");
        int countToPush = Integer.parseInt(integers[0]); //N
        int countToPop = Integer.parseInt(integers[1]); //S
        int elementToSearch = Integer.parseInt(integers[2]); //X
        int[] numbers = Arrays.stream(scanner.nextLine()
                        .split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < countToPush; i++) {
            stack.push(numbers[i]);
        }

        for (int i = 0; i < countToPop; i++) {
            stack.pop();
        }

        if (stack.isEmpty()) {
            System.out.println(0);
        } else if (stack.contains(elementToSearch)) {
            System.out.println("true");
        } else {
            System.out.println(Collections.min(stack));
            // System.out.println(stack.stream().mapToInt(e -> e).min().getAsInt());
        }

    }
}
