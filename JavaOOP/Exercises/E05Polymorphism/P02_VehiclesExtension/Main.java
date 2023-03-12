package E05Polymorphism.P02_VehiclesExtension;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Map<String, Vehicle> vehicles = new LinkedHashMap<>();
        createAndFillVehicles(scanner, vehicles);

        int n = Integer.parseInt(scanner.nextLine());

        while (n-- > 0) {
            String[] commandLine = scanner.nextLine().split("\\s+");
            String command = commandLine[0];
            String typeOfVehicle = commandLine[1];

            if ("Drive".equals(command)) {
                double distance = Double.parseDouble(commandLine[2]);
                vehicles.get(typeOfVehicle).drive(distance);

            } else if ("DriveEmpty".equals(command)) {
                double distance = Double.parseDouble(commandLine[2]);
                vehicles.get(typeOfVehicle).driveEmpty(distance);

            } else if ("Refuel".equals(command)) {
                double liters = Double.parseDouble(commandLine[2]);
                vehicles.get(typeOfVehicle).refuel(liters);

            }
        }

        vehicles.entrySet().stream()
                .map(entry -> String.format("%s: %.2f", entry.getKey(), entry.getValue().getFuelQuantity()))
                .forEach(System.out::println);


    }

    private static void createAndFillVehicles(Scanner scanner, Map<String, Vehicle> vehicles) {

        String[] vehicleInfo = scanner.nextLine().split("\\s+");

        String type = vehicleInfo[0];
        double fuelQuantity = Double.parseDouble(vehicleInfo[1]);
        double litersPerKm = Double.parseDouble(vehicleInfo[2]);
        double tankCapacity = Double.parseDouble(vehicleInfo[3]);

        if ("Car".equals(type)) {
            vehicles.put("Car", new Car(fuelQuantity, litersPerKm, tankCapacity));
        } else if ("Truck".equals(type)) {
            vehicles.put("Truck", new Truck(fuelQuantity, litersPerKm, tankCapacity));
        } else if ("Bus".equals(type)) {
            vehicles.put("Bus", new Bus(fuelQuantity, litersPerKm, tankCapacity));
        }

        if (vehicles.size() < 3) {
            createAndFillVehicles(scanner, vehicles);
        }

    }
}
