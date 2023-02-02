package Prep.JA_14122022;

import java.util.Scanner;

public class P02NavyBattle {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        String[][] matrix = new String[n][n];

        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = scanner.nextLine().split("");
        }

        int submarineRow = -1;
        int submarineCol = -1;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col].equals("S")) {
                    submarineRow = row;
                    submarineCol = col;
                    break;
                }
            }
        }

        String command = scanner.nextLine();

        int submarineLives = 3;
        int battlesWon = 0;

        while (true) {
            String currentElement = "";
            matrix[submarineRow][submarineCol] = "-";
            switch (command) {
                case "up":
                    //current row --
                    submarineRow--;
                    currentElement = matrix[submarineRow][submarineCol];
                    matrix[submarineRow][submarineCol] = "S";
                    break;
                case "down":
                    //current row ++
                    submarineRow++;
                    currentElement = matrix[submarineRow][submarineCol];
                    matrix[submarineRow][submarineCol] = "S";
                    break;
                case "left":
                    //current col --
                    submarineCol--;
                    currentElement = matrix[submarineRow][submarineCol];
                    matrix[submarineRow][submarineCol] = "S";
                    break;
                case "right":
                    //current col++
                    submarineCol++;
                    currentElement = matrix[submarineRow][submarineCol];
                    matrix[submarineRow][submarineCol] = "S";
                    break;
            }

            if (currentElement.equals("-")) {

            } else if (currentElement.equals("*")) {
                //mine
                submarineLives--;

                if (submarineLives <= 0) {
                    System.out.printf("Mission failed, U-9 disappeared! Last known coordinates [%d, %d]!\n", submarineRow, submarineCol);
                    printMatrix(matrix);
                    break;
                }
            } else if (currentElement.equals("C")) {
                battlesWon++;
                if (battlesWon >= 3) {
                    System.out.println("Mission accomplished, U-9 has destroyed all battle cruisers of the enemy!");
                    printMatrix(matrix);
                    break;
                }
            }

            command = scanner.nextLine();
        }
    }

    private static void printMatrix(String[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                System.out.print(matrix[r][c] + "");
            }
            System.out.println();
        }
    }

}