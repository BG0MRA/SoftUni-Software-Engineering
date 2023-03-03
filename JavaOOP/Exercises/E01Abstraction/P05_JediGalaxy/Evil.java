package E01Abstraction.P05_JediGalaxy;

public class Evil {

    public static void moveEvil(int row, int col, Field field) {

        while (row >= 0 && col >= 0) {

            if (field.isInBounds(row, col)) {
                field.setValue(row, col, 0);
            }

            row--;
            col--;
        }
    }
}
