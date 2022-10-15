package L05Lists;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P02GaussTrick {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Write a program that sum all numbers in a list in the following order:
        //first + last, first + 1 + last - 1, first + 2 + last - 2, … first + n, last - n.

        //input / output:
        // 1 2 3 4 5  >>  1 + 5 / 2 + 4 /  >> 6 6 3

        List<Integer> numsList = Arrays
                .stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int numsListSize = numsList.size();

        for (int i = 0; i < numsListSize / 2; i++) {
            int firstNumber = numsList.get(i);
            int secondNumber = numsList.get(numsList.size() - 1);

            numsList.set(i, firstNumber + secondNumber);
            numsList.remove(numsList.size() - 1);
        }

        for (int item : numsList) {
            System.out.printf("%d ", item);
        }

    }
}
