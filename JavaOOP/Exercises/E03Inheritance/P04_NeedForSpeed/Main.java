package E03Inheritance.P04_NeedForSpeed;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        double fuel = Double.parseDouble(scanner.nextLine());
        int horsePower = Integer.parseInt(scanner.nextLine());


        FamilyCar vehicle = new FamilyCar(fuel,horsePower);
        System.out.println(vehicle.getFuelConsumption());
//        vehicle.drive(100);
        System.out.println(vehicle.getFuel());
    }
}
