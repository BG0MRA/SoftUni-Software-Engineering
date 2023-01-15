package L02MultidimensionalArrays;

import java.util.Arrays;
import java.util.Scanner;

public class Demo extends P00MatrixUtils {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //input Data for Matrix
        //2 3
        //1 2 3
        //4 5 6

        String[] dimensions = scanner.nextLine().split("\\s+");
        int rows = Integer.parseInt(dimensions[0]);
        int cols = Integer.parseInt(dimensions[1]);

        int[][] matrix = new int[rows][cols];

//        int[][] matrix = readMatrix(scanner);

//        for (int row = 0; row < matrix.length; row++) {
//            String[] columnData = scanner.nextLine().split("\\s+");
//            for (int col = 0; col < matrix[row].length; col++) {
//                matrix[row][col] = Integer.parseInt(columnData[col]);
//            }
//        }

        for (int row = 0; row < matrix.length; row++) {
            int[] columnArr = Arrays
                    .stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            matrix[row] = columnArr;
        }

        printMatrix(matrix);
        System.out.println(matrix[0][0]);
        System.out.println(matrix[1][1]);
        System.out.println(matrix[1][2]);


    }
}
