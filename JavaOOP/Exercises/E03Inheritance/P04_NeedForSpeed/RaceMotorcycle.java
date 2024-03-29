package E03Inheritance.P04_NeedForSpeed;

public class RaceMotorcycle extends Motorcycle {

    public final static double DEFAULT_FUEL_CONSUMPTION = 8.0;

    public RaceMotorcycle(double fuel, int horsePower) {
        super(fuel, horsePower);
        setFuelConsumption(DEFAULT_FUEL_CONSUMPTION);
    }
}
