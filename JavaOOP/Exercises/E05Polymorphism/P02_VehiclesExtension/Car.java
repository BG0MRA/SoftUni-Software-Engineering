package E05Polymorphism.P02_VehiclesExtension;

public class Car extends Vehicle {

    public Car(double fuelQuantity, double litersPerKm, double tankCapacity) {
        super(fuelQuantity, litersPerKm, tankCapacity);
        setConsumptionIncrease(0.90);
        setFuelLoss(1.00);
    }
}
