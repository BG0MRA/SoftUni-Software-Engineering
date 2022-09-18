package E01BasicSyntax;

import java.util.Scanner;

public class P10PadawanEquipment {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // The amount of money George Lucas has – the floating-point number in the range [0.00…1000.00].
        // The count of students – integer in the range [0…100].
        // The price of lightsabers for a single saber – the floating-point number in the range [0.00…100.00].
        // The price of robes for a single robe – the floating-point number in the range [0.00…100.00].
        // The price of belts for a single belt – the floating-point number in the range [0.00…100.00].
        // The input data will always be valid. There is no need to check it explicitly.

        double movieBudget = Double.parseDouble(scanner.nextLine());
        int students = Integer.parseInt(scanner.nextLine());
        double lightSaberPrice = Double.parseDouble(scanner.nextLine());
        double robesPrice = Double.parseDouble(scanner.nextLine());
        double beltsPrice = Double.parseDouble(scanner.nextLine());

        //You will be given the amount of money George Lucas has, the number of students,
        // and the prices of each item. You have to help George Lucas calculate
        // if the money he has is enough to buy all of the equipment or how much more money he needs.
        //Because the lightsabers sometimes break, George Lucas should buy 10% more,
        // rounded up to the next integer. Also, every sixth belt is free.

        //Needed equipment for 2 padawans:
        //sabresPrice * (studentsCount + 10%) + robesPrice * (studentsCount)
        // + beltsPrice * (studentsCount - freeBelts)

        //If the calculated price of the equipment is less or equal to the money George Lucas has:
        //"The money is enough - it would cost {the cost of the equipment}lv."
        //If the calculated price of the equipment is more than the money George Lucas has:
        //"George Lucas will need {neededMoney}lv more."
        //All prices must be rounded to two digits after the decimal point.
        int freeBelts = (int) Math.floor(students / 6);

        int extraLightSabers = (int) Math.ceil(1.1 * students);
        double totalPriceForTheMovie = lightSaberPrice * extraLightSabers
                + (robesPrice * students)
                + (beltsPrice * (students - freeBelts));

        if (totalPriceForTheMovie <= movieBudget) {
            System.out.printf("The money is enough - it would cost %.2flv.", totalPriceForTheMovie);
        } else {
            System.out.printf("George Lucas will need %.2flv more.", (totalPriceForTheMovie - movieBudget));
        }

    }
}
