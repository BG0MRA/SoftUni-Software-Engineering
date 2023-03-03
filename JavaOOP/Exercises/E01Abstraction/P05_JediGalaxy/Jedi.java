package E01Abstraction.P05_JediGalaxy;

public class Jedi {

    public static long moveJedi(int row, int col, Field field) {

        long starsCollected = 0;

        while (row >= 0 && col < field.getInnerLength()) {

            if (field.isInBounds(row, col)) {
                starsCollected += field.getValue(row, col);
            }

            row--;
            col++;

        }

        return starsCollected;
    }
}
