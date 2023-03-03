package E01Abstraction.P04_TrafficLights;

public class TrafficLight {
    private Color currentColor;

    public TrafficLight(Color currentColor) {
        this.currentColor = currentColor;
    }

    public void changeColor() {
//        int nextColorOrdinal = this.currentColor.ordinal() + 1;
//
//        if (nextColorOrdinal >= Color.values().length) {
//            nextColorOrdinal = 0;
//        }
        int nextColorOrdinal = (this.currentColor.ordinal() + 1) % Color.values().length;

        Color nextColor = Color.values()[nextColorOrdinal];

        this.currentColor = nextColor;
    }

    public Color getCurrentColor() {
        return currentColor;
    }
}
