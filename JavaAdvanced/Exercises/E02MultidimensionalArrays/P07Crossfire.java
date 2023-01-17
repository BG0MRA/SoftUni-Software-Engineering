package E02MultidimensionalArrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class P07Crossfire {
    private static final Scanner scanner = new Scanner(System.in);
    private static int[][] matrix;
    private static List<Integer> rowsForRemoving = new ArrayList<>();

    public static void main(String[] args) {

        int[] dimensions = Arrays.stream(scanner.nextLine()
                        .split("\\s"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int rows = dimensions[0];
        int cols = dimensions[1];
        //fillMatrix
        matrix = new int[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = row * (cols) + col + 1;
            }
        }

        String commandInput = scanner.nextLine();
        while (!commandInput.equals("Nuke it from orbit")) {
            int targetRow = Integer.parseInt(commandInput.split("\\s")[0]);
            int targetCol = Integer.parseInt(commandInput.split("\\s")[1]);
            int radius = Integer.parseInt(commandInput.split("\\s")[2]);

            int startTop = Math.max(0, targetRow - radius);
            int endBottom = Math.min(matrix.length - 1, targetRow + radius);

            for (int row = startTop; row <= endBottom; row++) {
                if (isInBounds(row, targetCol) && row != targetRow) {
                    removeElement(row, targetCol);
                }
            }

            int endLeft = Math.max(0, targetCol - radius);
            int startRight = targetCol + radius;
            for (int col = startRight; col >= endLeft; col--) {
                if (isInBounds(targetRow, col)) {
                    removeElement(targetRow, col);
                }
            }

            commandInput = scanner.nextLine();
        }

        printMatrix(matrix);

    }

    private static void removeElement(int row, int col) {

        int rowLength = matrix[row].length;
        int elementForRemove = matrix[row][col];

        if (rowLength > 1) {
            int[] newRow = new int[rowLength - 1];
            for (int newCol = 0, oldCol = 0; oldCol < rowLength; oldCol++) {
                int currentElement = matrix[row][oldCol];
                if (currentElement != elementForRemove) {
                    newRow[newCol] = currentElement;
                    newCol++;
                }
            }
            matrix[row] = newRow;

        } else {
            int[][] newMatrix = new int[matrix.length - 1][];

            for (int oldRow = 0, newRow = 0; oldRow < matrix.length; oldRow++) {
                int currentRowLength = matrix[oldRow].length;
                if (currentRowLength > 1 || matrix[oldRow][col] != elementForRemove) {
                    newMatrix[newRow] = matrix[oldRow];
                    newRow++;
                }
            }
            matrix = newMatrix;


        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                System.out.print(matrix[r][c] + " ");
            }
            System.out.println();
        }
    }

    public static boolean isInBounds(int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }

}
