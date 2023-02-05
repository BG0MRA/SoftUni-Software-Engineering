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

//    public static void printMatrix(int[][] matrix) {
//        for (int r = 0; r < matrix.length; r++) {
//            for (int c = 0; c < matrix[r].length; c++) {
//                System.out.print(matrix[r][c] + " ");
//            }
//            System.out.println();
//        }
//    }


    public static boolean isInBounds(int row, int col, int[][] matrix) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }

    public static boolean isOutBounds(int row, int col, int[][] matrix) {
        return !isInBounds(row, col, matrix);
    }


//    Матрици - теория

//1. съхраняваме данни в матрица
    private static void fillMatrix(int[][] matrix, Scanner scanner) {
        for (int row = 0; row < matrix.length ; row++) {
            for (int col = 0; col < matrix.length; col++) {
                matrix[row][col] = scanner.nextInt();
            }
        }
    }

    private static void fillMatrix(double[][] matrix, Scanner scanner) {
        for (int row = 0; row < matrix.length ; row++) {
            for (int col = 0; col < matrix.length; col++) {
                matrix[row][col] = scanner.nextDouble();
            }
        }
    }

    private static void fillMatrix(String[][] matrix, Scanner scanner) {
        for (int row = 0; row < matrix.length ; row++) {
            //scanner.nextLine() -> "1 2 3"
            //scanner.nextLine().split(" ") -> ["1", "2", "3"]
            matrix[row] = scanner.nextLine().split("\\s+");
        }
    }
//2. извеждане на данни от матрица
    private static void printMatrix(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println(); //свали курсора на следващия ред
        }
    }
    private static void printMatrix(String[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println(); //свали курсора на следващия ред
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

// 3. диагонали в квадратна матрица
//  - главен диагонал -> индекс на ред = индекс на колона
//  - второстепенния диагонал -> индекс на колона = размер на матрица - индекс на ред - 1
// 4. валидираме индекси при правоъгълна матрица:
//  - брой редове -> matrix.lenght
//	- брой на колони -> matrix[0].lenght
//
// при квадратна матрица:
//  - брой редове -> matrix.lenght
//	- брой на колони -> matrix.lenght
//
//  - валиден ред: ред >= 0 и ред < брой на редове
//  - невалиден ред: ред < 0 или ред >= брой на редове
//
//  - валидна колона: колона >= 0 и колона < брой на колони
//  - валидна колона: колона < 0 или колона >= брой на колона
//
// 5. движения в матрица
//	- ляво с 1 позиция -> колона - 1
//  - дясно с 1 позиция -> колона + 1
//  - надолу с 1 позиция -> ред + 1
//  - нагоре с 1 позиция -> ред - 1

}
