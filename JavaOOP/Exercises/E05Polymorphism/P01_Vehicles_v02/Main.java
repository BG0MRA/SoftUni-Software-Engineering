package E05Polymorphism.P01_Vehicles_v02;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Map<String, Vehicle> vehicles = new LinkedHashMap<>();
        vehicles.put("Car", getVehicle(scanner));
        vehicles.put("Truck", getVehicle(scanner));

        int n = Integer.parseInt(scanner.nextLine());

        while (n-- > 0) {
            String[] commandLine = scanner.nextLine().split("\\s+");
            String command = commandLine[0];
            String typeOfVehicle = commandLine[1];
            double argument = Double.parseDouble(commandLine[2]);

            if ("Drive".equals(command)) {
                System.out.println(vehicles.get(typeOfVehicle).drive(argument));

            } else if ("Refuel".equals(command)) {
                vehicles.get(typeOfVehicle).refuel(argument);
            }
        }

        vehicles.values().forEach(System.out::println);

    }

    private static Vehicle getVehicle(Scanner scanner) {

        String[] vehicleInfo = scanner.nextLine().split("\\s+");
        String vehicleType = vehicleInfo[0];
        double fuelAmount = Double.parseDouble(vehicleInfo[1]);
        double fuelConsumption = Double.parseDouble(vehicleInfo[2]);

        switch (vehicleType) {
            case "Car":
                return new Car(fuelAmount, fuelConsumption);
            case "Truck":
                return new Truck(fuelAmount, fuelConsumption);
            default:
                throw new IllegalArgumentException("Missing vehicle");


        }
    }

}
