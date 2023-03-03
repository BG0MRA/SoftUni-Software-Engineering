package E02Encapsulation.P01_ClassBoxDataValidation;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double length = Double.parseDouble(scanner.nextLine());
        double width = Double.parseDouble(scanner.nextLine());
        double height = Double.parseDouble(scanner.nextLine());

        try {
            Box box = new Box(length, width, height);

            double surfaceArea = box.calculateSurfaceArea();
            double lateralSurfaceArea = box.calculateLateralSurfaceArea();
            double volume = box.calculateVolume();

            System.out.printf("Surface Area - %.2f\n", surfaceArea);
            System.out.printf("Lateral Surface Area - %.2f\n", lateralSurfaceArea);
            System.out.printf("Volume - %.2f\n", volume);

        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

    }
}
