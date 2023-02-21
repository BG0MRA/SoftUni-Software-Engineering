package Final;

import java.util.Scanner;

public class P02BlindManBuff {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] inputDimensions = scanner.nextLine().split("\\s+");
        int n = Integer.parseInt(inputDimensions[0]);
        int m = Integer.parseInt(inputDimensions[1]);

        String[][] playground = new String[n][m];

        fillMatrix(playground, scanner);

        int myPositionRow = -1;
        int myPositionCol = -1;

        for (int row = 0; row < playground.length; row++) {
            for (int col = 0; col < playground[0].length; col++) {
                if (playground[row][col].equals("B")){
                    myPositionRow = row;
                    myPositionCol = col;
                }
            }
        }

        int touchedPlayers = 0;
        boolean isGameFinished = false;
        int movesCount = 0;


        String command = scanner.nextLine();
        while (!command.equals("Finish")) {
            String currentElement = "";
            if (touchedPlayers >= 3) {
                isGameFinished = true;
                break;
            }
            // If the direction leads you out of the field, you need to stay in position inside the field(do NOT make the move)
            //If you have an obstacle, towards the direction, do NOT make the move and wait for the next command.
            if (command.equals("up")) {
                //row--
                if (isInBounds(myPositionRow - 1, myPositionCol, playground)) {
                    //still not moved take the element to step in, and if its "O" dont step
                    currentElement = playground[myPositionRow - 1][myPositionCol];
                    if (!currentElement.equals("O")){
                        //make the move
                        //first set - on old position
                        playground[myPositionRow][myPositionCol] = "-";
                        myPositionRow--;
                        movesCount++;
                        if (currentElement.equals("P")) {
                            touchedPlayers++;
                        }
                        playground[myPositionRow][myPositionCol] = "B";
                    }
                }

            } else if (command.equals("down")) {
                //row++
                if (isInBounds(myPositionRow + 1, myPositionCol, playground)) {
                    //still not moved take the element to step in, and if its "O" dont step
                    currentElement = playground[myPositionRow + 1][myPositionCol];
                    if (!currentElement.equals("O")){
                        //make the move
                        //first set - on old position
                        playground[myPositionRow][myPositionCol] = "-";
                        myPositionRow++;
                        movesCount++;
                        if (currentElement.equals("P")) {
                            touchedPlayers++;
                        }
                        playground[myPositionRow][myPositionCol] = "B";
                    }
                }

            } else if (command.equals("left")) {
                //col--
                if (isInBounds(myPositionRow, myPositionCol - 1, playground)) {
                    //still not moved take the element to step in, and if its "O" dont step
                    currentElement = playground[myPositionRow][myPositionCol - 1];
                    if (!currentElement.equals("O")){
                        //make the move
                        //first set - on old position
                        playground[myPositionRow][myPositionCol] = "-";
                        myPositionCol--;
                        movesCount++;
                        if (currentElement.equals("P")) {
                            touchedPlayers++;
                        }
                        playground[myPositionRow][myPositionCol] = "B";
                    }
                }
            } else if (command.equals("right")) {
                //col++
                if (isInBounds(myPositionRow, myPositionCol + 1, playground)) {
                    //still not moved take the element to step in, and if its "O" dont step
                    currentElement = playground[myPositionRow][myPositionCol + 1];
                    if (!currentElement.equals("O")){
                        //make the move
                        //first set - on old position
                        playground[myPositionRow][myPositionCol] = "-";
                        myPositionCol++;
                        movesCount++;
                        if (currentElement.equals("P")) {
                            touchedPlayers++;
                        }
                        playground[myPositionRow][myPositionCol] = "B";
                    }
                }
            }


            command = scanner.nextLine();
        }

        System.out.println("Game over!");
        System.out.printf("Touched opponents: %d Moves made: %d", touchedPlayers, movesCount);

    }

    private static void fillMatrix(String[][] matrix, Scanner scanner) {
        for (int row = 0; row < matrix.length; row++) {
            //scanner.nextLine() -> "1 2 3"
            //scanner.nextLine().split(" ") -> ["1", "2", "3"]
            matrix[row] = scanner.nextLine().split("\\s+");
        }
    }
    public static boolean isInBounds(int row, int col, String[][] matrix) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length;
    }


}
