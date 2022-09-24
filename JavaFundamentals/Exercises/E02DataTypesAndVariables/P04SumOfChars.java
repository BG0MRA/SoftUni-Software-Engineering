package E02DataTypesAndVariables;

import java.util.Scanner;

public class P04SumOfChars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int countSymbols = Integer.parseInt(scanner.nextLine());
        int sum = 0;
        for (int symbol = 0; symbol < countSymbols; symbol++) {
            char value = scanner.nextLine().charAt(0);
            sum += (int) value;
        }
        System.out.println("The sum equals: " + sum);
    }
}