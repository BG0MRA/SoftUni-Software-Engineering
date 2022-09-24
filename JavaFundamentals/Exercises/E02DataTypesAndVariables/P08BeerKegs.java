package E02DataTypesAndVariables;

import java.util.Scanner;

public class P08BeerKegs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Input data:  number of kegs
        //For each keg:
        //scan input data
        //calculate volume using: π * r^2 * h.
        //Compare and print the biggest Keg (volume)

        double maxVolume = Double.MIN_VALUE;
        String maxModelKeg = "";
        int countKegs = Integer.parseInt(scanner.nextLine());
        for (int keg = 0; keg < countKegs; keg++) {

            String kegModel = scanner.nextLine();
            double radius = Double.parseDouble(scanner.nextLine());
            int height = Integer.parseInt(scanner.nextLine());

            double volume = Math.PI * Math.pow(radius, 2) * height;

            if (volume > maxVolume) {
                maxVolume = volume;
                maxModelKeg = kegModel;
            }
        }

        System.out.println(maxModelKeg);
    }
}