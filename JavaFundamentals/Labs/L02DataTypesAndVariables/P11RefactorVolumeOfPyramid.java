package L02DataTypesAndVariables;

import java.util.Scanner;

public class P11RefactorVolumeOfPyramid {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Length: ");
        double length = Double.parseDouble(scanner.nextLine());
        System.out.print("Width: ");
        double width = Double.parseDouble(scanner.nextLine());
        System.out.print("Height: ");
        double hight = Double.parseDouble(scanner.nextLine());
        double volume = (length * width * hight) / 3;
        System.out.printf("Pyramid Volume: %.2f", volume);
    }
}
