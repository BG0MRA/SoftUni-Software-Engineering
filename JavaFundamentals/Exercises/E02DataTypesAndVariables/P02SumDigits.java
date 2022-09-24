package E02DataTypesAndVariables;

import java.util.Scanner;

public class P02SumDigits {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //You will be given a single integer. Your task is to find the sum of its digits.
        int inputNumber = Integer.parseInt(scanner.nextLine());
        int sum = 0;
        while (inputNumber > 0) {
            int lastDigit = inputNumber % 10;
            sum += lastDigit;
            inputNumber = inputNumber / 10;
        }
        System.out.println(sum);
    }
}