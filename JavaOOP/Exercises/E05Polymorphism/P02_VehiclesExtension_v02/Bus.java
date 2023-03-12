package E05Polymorphism.P02_VehiclesExtension_v02;

public class Bus extends Vehicle {
    public final static double AC_ADDITIONAL_CONSUMPTION = 1.40;

    public Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity, AC_ADDITIONAL_CONSUMPTION);
    }

}
