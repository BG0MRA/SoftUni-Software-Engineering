package E05Polymorphism.P01_Vehicles_v02;

import java.text.DecimalFormat;

public abstract class Vehicle {

    private double fuelQuantity;
    private double fuelConsumption;
//    private Double summerIncrease;
//    private Double fuelLoss;

    public Vehicle(double fuelQuantity, double fuelConsumption) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
    }

    public String drive(double distance) {
        double fuelNeeded = distance * this.fuelConsumption;

        if (getFuelQuantity() < fuelNeeded) {
            return getTypeOfVehicle() + " needs refueling";

        } else {
            DecimalFormat df = new DecimalFormat("##.##");
            setFuelQuantity(getFuelQuantity() - fuelNeeded);
            return String.format("%s travelled %s km", getTypeOfVehicle(), df.format(distance));
        }
    }

    public void refuel(double litres) {
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
