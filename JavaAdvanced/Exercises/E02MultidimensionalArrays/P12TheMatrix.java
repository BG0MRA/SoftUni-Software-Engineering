package E02MultidimensionalArrays;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class P12TheMatrix {
    private static char[][] matrix;
    private static char fillChar;
    private static char startChar;
    private static ArrayDeque<int[]> queue = new ArrayDeque<>();


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
        int rows = dimensions[0];
        int cols = dimensions[1];
        matrix = new char[rows][cols];

        for (int row = 0; row < rows; row++) {
            matrix[row] = scanner.nextLine()
                    .replaceAll("\\s+", "")
                    .toCharArray();
        }

        fillChar = scanner.nextLine().charAt(0);
        int[] coordinates = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
        int startRow = coordinates[0];
        int startCol = coordinates[1];
        startChar = matrix[startRow][startCol];

        checkAndReplaceIfFindIt(startRow, startCol);

        printMatrix();
    }


    private static void checkAndReplaceIfFindIt(int row, int col) {

        int[] pointCoordinates = new int[]{row, col};
        queue.offer(pointCoordinates);

        checkNextPoitFromQueue();
    }

    private static void checkNextPoitFromQueue() {

        while (!queue.isEmpty()) {
            int[] pointCoordinates = queue.poll();
            int row = pointCoordinates[0];
            int col = pointCoordinates[1];
            matrix[row][col] = fillChar;

            if (row + 1 < matrix.length && matrix[row + 1][col] == startChar) {
                matrix[row + 1][col] = fillChar;
                queue.offer(new int[]{row + 1, col});
            }

            if (col + 1 < matrix[row].length && matrix[row][col + 1] == startChar) {
                matrix[row][col + 1] = fillChar;
                queue.offer(new int[]{row, col + 1});
            }

            if (row - 1 >= 0 && matrix[row - 1][col] == startChar) {
                matrix[row - 1][col] = fillChar;
                queue.offer(new int[]{row - 1, col});
            }

            if (col - 1 >= 0 && matrix[row][col - 1] == startChar) {
                matrix[row][col - 1] = fillChar;
                queue.offer(new int[]{row, col - 1});
            }
        }
    }

    private static void printMatrix() {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }
}
