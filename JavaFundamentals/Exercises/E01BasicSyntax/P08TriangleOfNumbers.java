package E01BasicSyntax;

import java.util.Scanner;

public class P08TriangleOfNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Write a program that receives a number – n and prints a triangle from 1 to n as in the examples.
        //n will be in the interval [1...20].

        int number = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= number ; i++) {
            for (int j = 1; j <= i ; j++) {
                System.out.printf("%d ",i);
            }
            System.out.println();
        }
    }
}
