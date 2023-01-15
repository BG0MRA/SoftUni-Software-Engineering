package L02MultidimensionalArrays;

import java.util.Arrays;
import java.util.Scanner;

public class P00MatrixUtils {
    public static int[][] readMatrix(Scanner scanner) {
        String[] dimensions = scanner.nextLine().split(" ");
        int rows = Integer.parseInt(dimensions[0]);
        int cols = Integer.parseInt(dimensions[1]);
        int[][] matrix = new int[rows][cols];
        for (int row = 0; row < rows; row++) {
            int[] arr = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            matrix[row] = arr;
        }

        return matrix;
    }

    public static void printMatrix(int[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                System.out.print(matrix[r][c] + " ");
            }
            System.out.println();
        }
    }

    public static void printMatrix(char[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                System.out.print(matrix[r][c] + " ");
            }
            System.out.println();
        }
    }

    public static boolean isInBounds(int row, int col, int[][] matrix) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }

    public static boolean isOutBounds(int row, int col, int[][] matrix) {
        return !isInBounds(row, col, matrix);
    }
}
