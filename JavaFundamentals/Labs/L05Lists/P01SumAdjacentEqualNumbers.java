package L05Lists;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P01SumAdjacentEqualNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Write a program to sum all adjacent equal numbers
        // in a list of decimal numbers, starting from left to right.

        //After two numbers are summed, the obtained result
        // could be equal to some of its neighbors and should be summed as well (see the examples below).

        //Always sum the leftmost two equal neighbors (if several couples of equal neighbors are available).

        List<Double> numbersList = Arrays
                .stream(scanner.nextLine().split(" "))
                .map(Double::parseDouble)
                .collect(Collectors.toList());

        // operations needed:
        //3 3 6 1 >> 6 6 1 >> 12 1
        for (int i = 0; i < numbersList.size() - 1; i++) {
            double currentNum = numbersList.get(i);
            double nextNum = numbersList.get(i + 1);

            if (currentNum == nextNum) {
                double sumNumbers = currentNum + nextNum;
                numbersList.set(i, sumNumbers);
                numbersList.remove(i + 1);
                i = -1;
            }
        }
        System.out.println(joinElementsByDelimiter(numbersList, " "));

    }

    private static String joinElementsByDelimiter(List<Double> list, String delimiter) {
        DecimalFormat df = new DecimalFormat("0.#");
        String output = "";

        for( double item : list) {
            String numDf = df.format(item) + delimiter;
            output += numDf;
        }
        return output;
    }
}
