package E05Polymorphism.P01_Vehicles;

import java.text.DecimalFormat;

public abstract class Vehicle {

    private double fuelQuantity;
    private double litersPerKm;
    private Double summerIncrease;
    private Double fuelLoss;

    public Vehicle(double fuelQuantity, double fuelConsumptionPerKm) {
        this.fuelQuantity = fuelQuantity;
        this.litersPerKm = fuelConsumptionPerKm;
    }

    public void drive(double distance) {
        double summerFuelConsumption = (litersPerKm + summerIncrease);
        double requiredFuel = distance * summerFuelConsumption;

        String typeofVehicle = getClass().getSimpleName();
        DecimalFormat df = new DecimalFormat("0.##");

        if (fuelQuantity >= requiredFuel) {
            fuelQuantity -= requiredFuel;
            System.out.printf("%s travelled %s km\n", typeofVehicle, df.format(distance));

        } else {
            System.out.printf("%s needs refueling\n", typeofVehicle);
        }

    }

    public void refuel(double litres) {
        fuelQuantity += litres * fuelLoss;
    }

    public double getFuelQuantity() {
        return this.fuelQuantity;
    }

    public double getLitersPerKm() {
        return this.litersPerKm;
    }

    protected final void setSummerIncrease(double summerIncrease) {
        if (this.summerIncrease == null) {
            this.summerIncrease = summerIncrease;
        }
    }

    protected final void setFuelLoss(double fuelLoss) {
        if (this.fuelLoss == null) {
            this.fuelLoss = fuelLoss;
        }
    }
}
