package E05Polymorphism.P02_VehiclesExtension;

import java.text.DecimalFormat;

public abstract class Vehicle {

    private double fuelQuantity;
    private double litersPerKm;
    private double tankCapacity;
    private double consumptionIncrease;
    private double fuelLoss;

    public Vehicle(double fuelQuantity, double fuelConsumptionPerKm, double tankCapacity) {
//        setFuelQuantity(fuelQuantity);
        this.fuelQuantity = fuelQuantity;
        this.litersPerKm = fuelConsumptionPerKm;
        this.tankCapacity = tankCapacity;
    }


    public void drive(double distance) {
        double summerFuelConsumption = (litersPerKm + consumptionIncrease);
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

    public void driveEmpty(double distance) {
        double tmp = this.consumptionIncrease;
        setConsumptionIncrease(0.00);
        drive(distance);
        setConsumptionIncrease(tmp);
    }

    public void refuel(double litres) {
        if (litres <= 0) {
            System.out.println("Fuel must be a positive number");
        } else {
            double totalFuelQuantity = fuelQuantity + litres * fuelLoss;
            if (totalFuelQuantity > tankCapacity) {
                System.out.println("Cannot fit fuel in tank");
            } else {
                this.fuelQuantity = totalFuelQuantity;
            }
        }
    }

    public double getFuelQuantity() {
        return this.fuelQuantity;
    }

    public double getLitersPerKm() {
        return this.litersPerKm;
    }

    public double getTankCapacity() {
        return tankCapacity;
    }

//    public void setFuelQuantity(double fuelQuantity) {
//        if (fuelQuantity < 0) {
//            System.out.println("Fuel must be a positive number");
//        } else if (fuelQuantity > tankCapacity) {
//            System.out.println("Cannot fit fuel in tank");
//        } else {
//            this.fuelQuantity = fuelQuantity;
//        }
//    }

    protected final void setConsumptionIncrease(double consumptionIncrease) {
        this.consumptionIncrease = consumptionIncrease;
    }

    protected final void setFuelLoss(double fuelLoss) {
        this.fuelLoss = fuelLoss;
    }
}
