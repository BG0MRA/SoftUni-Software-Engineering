package E06DefiningClasses.P04RawData;

import java.util.List;

public class Car {

    private String model;
    private Engine engine;
    private Cargo cargo;
    private List<Tire> tiresList;

    public Car(String model, Engine engine, Cargo cargo, List<Tire> tiresList) {
        this.model = model;
        this.engine = engine;
        this.cargo = cargo;
        this.tiresList = tiresList;
    }

    public String getModel() {
        return this.model;
    }

    public Engine getEngine() {
        return this.engine;
    }

    public Cargo getCargo() {
        return this.cargo;
    }

    public List<Tire> getTires() {
        return this.tiresList;
    }

}
