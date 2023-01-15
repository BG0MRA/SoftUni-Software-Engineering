package L02MultidimensionalArrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P07FindTheRealQueen {
    public static List<Integer> rowsIndicesWith1Queen = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        char matrix[][] = new char[8][8];

        for (int row = 0; row < matrix.length; row++) {
            String inputRow = scanner.nextLine().replace(" ", "");
            if (inputRow.length() - inputRow.replace("q", "").length() == 1) {
                rowsIndicesWith1Queen.add(row);
            }
            matrix[row] = inputRow.toCharArray();
        }

        String output = "not found";
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == 'q') {
                    output = isRealQueen(row, col, matrix) ? String.format(row + " " + col) : "not found";
                }
                if (!output.equals("not found")) {
                    break;
                }
            }
            if (!output.equals("not found")) {
                break;
            }
        }

        System.out.println(output);
    }

    private static boolean isRealQueen(int row, int col, char[][] matrix) {

        //ако редът не е от списъка с редове с 1бр queen
        if (!rowsIndicesWith1Queen.contains(row)) {
            return false;
        }


        //проверка за queen по вертикал
        for (int currRow = 0; currRow < matrix.length; currRow++) {
            if (currRow == row) {
                continue;
            }
            if (matrix[currRow][col] == 'q') {
                return false;
            }
        }

        //проверка за queen по диагонал от г.ляво към д.дясно
        int currRow = 0;
        int currCol = 0;
        if (row > col) {
            currRow = row - col;
        } else {
            currCol = col - row;
        }
        while (isInBounds(currRow, currCol, matrix)) {
            if (currRow == row && currCol == col) {
                currRow++;
                currCol++;
                continue;
            }
            if (matrix[currRow][currCol] == 'q') {
                return false;
            }
            currRow++;
            currCol++;
        }

        //проверка за queen по диагонал от д.ляво към г.дясно
        currRow = matrix.length - 1;
        currCol = 0;
        int rowsToTheEndOFMatrix = matrix.length - 1 - row;
        int colsToTheBeginOfMatrix = col - 0;

        if (rowsToTheEndOFMatrix > colsToTheBeginOfMatrix) {
            currRow -= rowsToTheEndOFMatrix - colsToTheBeginOfMatrix;
        } else {
            currCol += colsToTheBeginOfMatrix - rowsToTheEndOFMatrix;
        }
        while (isInBounds(currRow, currCol, matrix)) {
            if (currRow == row && currCol == col) {
                currRow--;
                currCol++;
                continue;
            }
            if (matrix[currRow][currCol] == 'q') {
                return false;
            }
            currRow--;
            currCol++;
        }

        return true;
    }

    private static boolean isInBounds(int row, int col, char[][] matrix) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }

}
