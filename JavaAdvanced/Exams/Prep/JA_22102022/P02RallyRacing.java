package Prep.JA_22102022;

import java.util.Scanner;

public class P02RallyRacing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        String racingNumber = scanner.nextLine();

        String[][] traceMatrix = new String[n][n];

        fillMatrix(traceMatrix, scanner);

        //The tracked race car always starts with coordinates [0, 0].


        int startTunnelRow = -1;
        int startTunnelCol = -1;
        int endTunnelRow = -1;
        int endTunnelCol = -1;

        boolean isFirst = true;
        for (int row = 0; row < traceMatrix.length; row++) {
            for (int col = 0; col < traceMatrix[0].length; col++) {
                if (traceMatrix[row][col].equals("T")) {
                    if (isFirst) {
                        startTunnelRow = row;
                        startTunnelCol = col;
                        isFirst = false;
                    } else {
                        endTunnelRow = row;
                        endTunnelCol = col;
                    }
                }
            }

        }

        int carRow = 0;
        int carCol = 0;
        //every move is 10km if its tunnel 30km
        int passedKm = 0;
        boolean isFinished = false;

        String command = scanner.nextLine();
        while (!command.equals("End") && !isFinished) {
            String currentElement = "";

            if (command.equals("left")) {
                //col -1
                traceMatrix[carRow][carCol] = ".";
                carCol -= 1;
                currentElement = traceMatrix[carRow][carCol];
                //traceMatrix[carRow][carCol] = ".";

            } else if (command.equals("right")) {
                //col +1
                traceMatrix[carRow][carCol] = ".";
                carCol += 1;
                currentElement = traceMatrix[carRow][carCol];
                //traceMatrix[carRow][carCol] = ".";

            } else if (command.equals("up")) {
                //row -1
                traceMatrix[carRow][carCol] = ".";
                carRow -= 1;
                currentElement = traceMatrix[carRow][carCol];
                //traceMatrix[carRow][carCol] = ".";

            } else if (command.equals("down")) {
                // row +1
                traceMatrix[carRow][carCol] = ".";
                carRow += 1;
                currentElement = traceMatrix[carRow][carCol];
                //traceMatrix[carRow][carCol] = ".";
            }

            if (currentElement.equals(".")) {
                passedKm += 10;
                traceMatrix[carRow][carCol] = "C";
            } else if (currentElement.equals("T")) {
                traceMatrix[carRow][carCol] = ".";
                carRow = endTunnelRow;
                carCol = endTunnelCol;
                traceMatrix[carRow][carCol] = "C";
                passedKm += 30;
            } else if (currentElement.equals("F")) {
                traceMatrix[carRow][carCol] = "C";
                passedKm += 10;
                isFinished = true;
            }

            command = scanner.nextLine();
        }

        if (isFinished) {
            System.out.printf("Racing car %s finished the stage!\n", racingNumber);
        } else {
            System.out.printf("Racing car %s DNF.\n", racingNumber);
        }

        System.out.printf("Distance covered %d km.\n", passedKm);

        printMatrix(traceMatrix);

    }


    private static void fillMatrix(String[][] matrix, Scanner scanner) {
        for (int row = 0; row < matrix.length; row++) {
            //scanner.nextLine() -> "1 2 3"
            //scanner.nextLine().split(" ") -> ["1", "2", "3"]
            matrix[row] = scanner.nextLine().split("\\s+");
        }
    }

    private static void printMatrix(String[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                System.out.print(matrix[row][col] + "");
            }
            System.out.println(); //свали курсора на следващия ред
        }
    }
}
