package E01StacksAndQueues;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class P03MaximumElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            int[] command = Arrays.stream(scanner.nextLine()
                            .split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            switch (command[0]) {
                case 1: //Push the element X into the stack
                    stack.push(command[1]);
                    break;
                case 2: //Delete the element present at the top of the stack.
                    stack.pop();
                    break;
                case 3: //Print the maximum element in the stack.
                    System.out.println(Collections.max(stack));
                    break;
            }

        }

    }
}
