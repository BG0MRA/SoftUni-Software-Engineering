package E05Polymorphism.P02_VehiclesExtension;


public class Bus extends Vehicle {

    public Bus(double fuelQuantity, double litersPerKm, double tankCapacity) {
        super(fuelQuantity, litersPerKm, tankCapacity);
        setConsumptionIncrease(1.40);
        setFuelLoss(1.00);
    }
}
