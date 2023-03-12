package E05Polymorphism.P01_Vehicles;

public class Car extends Vehicle {

    public Car(double fuelQuantity, double litersPerKm) {
        super(fuelQuantity, litersPerKm);
        setSummerIncrease(0.90);
        setFuelLoss(1.00);
    }
}
