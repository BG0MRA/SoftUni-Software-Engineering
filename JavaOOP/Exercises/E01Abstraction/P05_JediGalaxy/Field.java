package E01Abstraction.P05_JediGalaxy;

public class Field {
    private long[][] matrix;

    public Field(int rows, int cols) {
        this.matrix = new long [rows][cols];
        setFieldValues();
    }

    private void setFieldValues() {
        long value = 0;
        for (int row = 0; row < this.matrix.length; row++) {
            for (int col = 0; col < this.matrix[row].length; col++) {
                this.matrix[row][col] = value++;
            }
        }
    }

    public long getValue(int row, int col) {
        return this.matrix[row][col];
    }

    public void setValue(int row, int col, long newValue) {
        this.matrix[row][col] = newValue;
    }

    public int getInnerLength() {
        return matrix[1].length;
    }

    public boolean isInBounds(int r, int c) {
        return r >= 0 && r < matrix.length && c >= 0 && c < matrix[r].length;
    }


}
