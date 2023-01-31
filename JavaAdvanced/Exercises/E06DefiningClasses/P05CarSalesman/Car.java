package E06DefiningClasses.P05CarSalesman;

import java.util.Map;

public class Car {
    private String model;
    private Engine engine;
    private String weight;
    private String color;

    Car(String[] carInput, Map<String, Engine> enginesMap) {
        this.model = carInput[0];
        this.engine = enginesMap.get(carInput[1]);

        this.weight = "n/a";
        this.color = "n/a";

        if (carInput.length == 3) {
            if (carInput[2].matches("\\d+")) {
                this.weight = carInput[2];
            } else {
                this.color = carInput[2];
            }
        }

        if (carInput.length == 4) {
            this.weight = carInput[2];
            this.color = carInput[3];
        }
    }

    @Override
    public String toString() {
        return String.format("%s:%n" +
                        "%s%n" +
                        "Weight: %s%n" +
                        "Color: %s"
                , this.model, this.engine.toString(), this.weight, this.color);

    }
}
