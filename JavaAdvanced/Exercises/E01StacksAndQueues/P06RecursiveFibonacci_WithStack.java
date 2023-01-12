package E01StacksAndQueues;

import java.util.ArrayDeque;
import java.util.Scanner;

public class P06RecursiveFibonacci_WithStack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        //0 1 2 3 4 5  6  7  8  9 10  11
        //1 1 2 3 5 8 13 21 34 55 89 144
        if (n < 2) {
            System.out.println(1);
        } else {
            ArrayDeque<Long> stack = new ArrayDeque<>();
            stack.push(0l);
            stack.push(1l);
            for (int i = 1; i <= n; i++) {
                long num1 = stack.pop();
                long num2 = stack.pop();
                stack.push(num1);
                stack.push(num1 + num2);
            }
            System.out.println(stack.peek());
        }

    }

}
