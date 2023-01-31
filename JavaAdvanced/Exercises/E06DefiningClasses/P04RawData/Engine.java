package E06DefiningClasses.P04RawData;

public class Engine {

    private int speed;
    private int power;

    Engine(int speed, int power) {
        this.speed = speed;
        this.power = power;
    }

    public int getPower () {
        return this.power;
    }

}
