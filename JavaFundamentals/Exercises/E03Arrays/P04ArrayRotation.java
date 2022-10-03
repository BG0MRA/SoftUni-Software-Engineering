package E03Arrays;

import java.util.Arrays;
import java.util.Scanner;

public class P04ArrayRotation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Write a program that receives an array
        // and the number of rotations you have to perform
        // (the first element goes at the end).
        // Print the resulting array.

        //input: 51 47 32 61 21
        //rotations: 2
        //output: 32 61 21 51 47

        int[] numArr = Arrays
                .stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int numRotations = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numRotations; i++) {
            int firstElement = numArr[0];

            for (int j = 0; j < numArr.length - 1; j++) {
                numArr[j] = numArr[j + 1];

            }

            numArr[numArr.length - 1] = firstElement;
        }

        for(int num : numArr) {
            System.out.printf(num + " ");
        }

    }
}
