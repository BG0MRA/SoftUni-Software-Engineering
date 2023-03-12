package E05Polymorphism.P02_VehiclesExtension_v02;

public class Car extends Vehicle {
    public final static double AC_ADDITIONAL_CONSUMPTION = 0.90;

    public Car(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity, AC_ADDITIONAL_CONSUMPTION);
    }

}
