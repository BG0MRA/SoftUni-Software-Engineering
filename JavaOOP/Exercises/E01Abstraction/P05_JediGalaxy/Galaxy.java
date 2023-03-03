package E01Abstraction.P05_JediGalaxy;

public class Galaxy {
    private Field field;

    public Galaxy(Field field) {
        this.field = field;
    }


    public void moveEvil(int rowEvil, int colEvil) {
        Evil.moveEvil(rowEvil, colEvil, this.field);
    }

    public long moveJedi(int rowJedi, int colJedi) {
        return Jedi.moveJedi(rowJedi, colJedi, this.field);
    }


}
