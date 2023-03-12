package E05Polymorphism.P01_Vehicles_v02;

import java.text.DecimalFormat;

public class Truck extends Vehicle {
    public final static double AC_ADDITIONAL_CONSUMPTION = 1.60;
    public final static double FUEL_AFTER_DEDUCTION_DUE_HOLE = 0.95;

    public Truck(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption);
        setFuelConsumption(getFuelConsumption() + AC_ADDITIONAL_CONSUMPTION);
    }

       @Override
    public void refuel(double litres) {
        super.refuel(litres * FUEL_AFTER_DEDUCTION_DUE_HOLE);
    }


}
