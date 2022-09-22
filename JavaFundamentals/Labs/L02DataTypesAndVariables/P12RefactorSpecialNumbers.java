package L02DataTypesAndVariables;

import java.util.Scanner;

public class P12RefactorSpecialNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = Integer.parseInt(scanner.nextLine());
        for (int i = 1; i <= number; i++) {
            int currentSum = i;
            int sum = 0;
            while (i > 0) {
                sum += i % 10;
                i = i / 10;
            }
            boolean isSpecialNumber = (sum == 5) || (sum == 7) || (sum == 11);
            if (isSpecialNumber) {
                System.out.printf("%d -> True%n", currentSum);
            } else {
                System.out.printf("%d -> False%n", currentSum);
            }
            i = currentSum;
        }
    }
}