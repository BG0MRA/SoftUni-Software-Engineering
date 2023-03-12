package E05Polymorphism.P02_VehiclesExtension_v02;

import java.text.DecimalFormat;

public abstract class Vehicle {


    private double fuelQuantity;
    private double fuelConsumption;
    private double tankCapacity;
    private double additionalACConsumption;


    public Vehicle(double fuelQuantity, double fuelConsumption, double tankCapacity, double additionalACConsumption) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
        this.tankCapacity = tankCapacity;
        this.additionalACConsumption = additionalACConsumption;
    }

    public String drive(double distance) {
        double fuelNeeded = distance * (this.fuelConsumption);

        if (getFuelQuantity() < fuelNeeded) {
            return getTypeOfVehicle() + " needs refueling";

        } else {
            DecimalFormat df = new DecimalFormat("##.##");
            setFuelQuantity(getFuelQuantity() - fuelNeeded);
            return String.format("%s travelled %s km", getTypeOfVehicle(), df.format(distance));
        }
    }

    public String driveAC(double distance) {
        this.fuelConsumption += this.additionalACConsumption;
        String driveResult = this.drive(distance);
        this.fuelConsumption -= this.additionalACConsumption;

        return driveResult;
    }

    public void refuel(double litres) {

        if (litres <= 0) {
            throw new IllegalArgumentException("Fuel must be a positive number");

        } else if (fuelQuantity + litres > tankCapacity) {
            throw new IllegalArgumentException("Cannot fit fuel in tank");
        }

        this.fuelQuantity += litres;
    }

    protected void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public double getFuelConsumption() {
        return this.fuelConsumption;
    }

    protected void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    public double getFuelQuantity() {
        return this.fuelQuantity;
    }

    private String getTypeOfVehicle() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f", getTypeOfVehicle(), fuelQuantity);
    }
}
