package E05Polymorphism.P02_VehiclesExtension_v02;

public class Truck extends Vehicle {
    public final static double AC_ADDITIONAL_CONSUMPTION = 1.60;
    public final static double FUEL_AFTER_DEDUCTION_DUE_HOLE = 0.95;

    public Truck(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity, AC_ADDITIONAL_CONSUMPTION);
    }

    @Override
    public void refuel(double litres) {
        super.refuel(litres * FUEL_AFTER_DEDUCTION_DUE_HOLE);
    }


}
