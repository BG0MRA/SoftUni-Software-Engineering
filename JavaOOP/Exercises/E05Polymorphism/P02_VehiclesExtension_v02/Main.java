package E05Polymorphism.P02_VehiclesExtension_v02;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Map<String, Vehicle> vehicles = new LinkedHashMap<>();
        vehicles.put("Car", getVehicle(scanner));
        vehicles.put("Truck", getVehicle(scanner));
        vehicles.put("Bus", getVehicle(scanner));

        int n = Integer.parseInt(scanner.nextLine());

        while (n-- > 0) {
            String[] commandLine = scanner.nextLine().split("\\s+");
            String command = commandLine[0];
            String typeOfVehicle = commandLine[1];
            double argument = Double.parseDouble(commandLine[2]);

            switch (command) {
                case "DriveEmpty":
                    System.out.println(vehicles.get(typeOfVehicle).drive(argument));
                    break;
                case "Drive":
                    System.out.println(vehicles.get(typeOfVehicle).driveAC(argument));
                    break;
                case "Refuel":
                    try {
                        vehicles.get(typeOfVehicle).refuel(argument);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }

        vehicles.values().forEach(System.out::println);
    }

    private static Vehicle getVehicle(Scanner scanner) {

        String[] vehicleInfo = scanner.nextLine().split("\\s+");
        String vehicleType = vehicleInfo[0];
        double fuelAmount = Double.parseDouble(vehicleInfo[1]);
        double fuelConsumption = Double.parseDouble(vehicleInfo[2]);
        double tankCapacity = Double.parseDouble(vehicleInfo[3]);

        switch (vehicleType) {
            case "Car":
                return new Car(fuelAmount, fuelConsumption, tankCapacity);
            case "Truck":
                return new Truck(fuelAmount, fuelConsumption, tankCapacity);
            case "Bus":
                return new Bus(fuelAmount, fuelConsumption, tankCapacity);
            default:
                throw new IllegalArgumentException("Missing vehicle");


        }
    }

}
