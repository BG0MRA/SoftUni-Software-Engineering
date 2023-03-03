package E03Inheritance.P04_NeedForSpeed;

public class SportCar extends Car {
    public final static double DEFAULT_FUEL_CONSUMPTION = 10.0;

    public SportCar(double fuel, int horsePower) {
        super(fuel, horsePower);
        setFuelConsumption(DEFAULT_FUEL_CONSUMPTION);
    }
}
