package E05Polymorphism.P01_Vehicles_v02;

import java.text.DecimalFormat;

public class Car extends Vehicle {
    public final static double AC_ADDITIONAL_CONSUMPTION = 0.90;

    public Car(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption);
        setFuelConsumption(getFuelConsumption() + AC_ADDITIONAL_CONSUMPTION);
    }

}
