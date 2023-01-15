package L02MultidimensionalArrays;

import java.util.Arrays;
import java.util.Scanner;

public class P08WrongMeasurements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = Integer.parseInt(scanner.nextLine());
        int[][] matrix = new int[rows][];
        for (int row = 0; row < rows; row++) {
            matrix[row] = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int[] indexOfWrongElement = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int wrongValue = matrix[indexOfWrongElement[0]][indexOfWrongElement[1]];

        int[][] correctedMatrix = new int[matrix.length][matrix[0].length];
        for (int row = 0; row < correctedMatrix.length; row++) {
            for (int col = 0; col < correctedMatrix[row].length; col++) {
                int correctedValue = matrix[row][col];
                if (correctedValue == wrongValue) {
                    correctedValue = getCorrectedValue(row, col, matrix, wrongValue);
                }
                correctedMatrix[row][col] = correctedValue;
            }
        }

        printMatrix(correctedMatrix);


    }

    private static int getCorrectedValue(int row, int col, int[][] matrix, int wrongValue) {
        int correctedValue = 0;
        int[] indexChange = new int[]{-1, 1, 0, 0};
        int j = indexChange.length - 1;
        for (int i = 0; i < indexChange.length; i++) {
            int currRow = row + indexChange[i];
            int currCol = col + indexChange[j];
            j--;

            if (isInBounds(currRow, currCol, matrix)) {
                int element = matrix[currRow][currCol];
                if (element != wrongValue) {
                    correctedValue += element;
                }
            }
        }

        return correctedValue;

    }

    public static void printMatrix(int[][] matrix) {
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
}
