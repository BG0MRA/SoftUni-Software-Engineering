package L01Abstraction;

import java.util.Scanner;

public class P01_RhombusOfStars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        printRhombus(n);

//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= n - i; j++) {
//                System.out.print(" ");
//            }
//            for (int j = 1; j <= i; j++) {
//                System.out.print("* ");
//            }
//            System.out.println();
//        }
//
//        for (int i = 1; i <= n - 1; i++) {
//            for (int j = 1; j <= i; j++) {
//                System.out.print(" ");
//            }
//            for (int j = 1; j <= n - i; j++) {
//                System.out.print("* ");
//            }
//            System.out.println();
//        }
    }

    public static void printRhombus(int size) {
        for (int i = 1; i <= size; i++) {
            printTriangle(size - i, i);
        }
        for (int i = 1; i <= size - 1; i++) {
            printTriangle(i, size - i);
        }
    }

    private static void printTriangle (int firstCount, int secondCount) {
        //print decreasingPattern
        for (int i = 1; i <= firstCount; i++) {
            System.out.print(" ");
        }
        //print increasingPattern
        for (int i = 1; i <= secondCount; i++) {
            System.out.print("* ");
        }
        System.out.println();
    }

}
