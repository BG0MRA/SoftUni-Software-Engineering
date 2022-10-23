package midExam;

import java.util.Scanner;

public class P02SpaceTravel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //1.first line string separated by pipes "||" , first element command, second element an integer
        //2.an integer represents the starting amount of fuel
        //3.an integer represents the starting amount of ammunition.

        //1.Travel 10||Enemy 30||Repair 15||Titan
        String[] commandArr = scanner.nextLine().split("\\|\\|");

        //2. integer amount of fuel
        int fuelInput = Integer.parseInt(scanner.nextLine());

        //3. integer amount of ammunition
        int ammunitionInput = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < commandArr.length; i++) {
            //split local command
            String[] tokens = commandArr[i].split(" ");

            if (commandArr[i].contains("Travel")) {
                //Travel 10 - tokens[1] - light years
                int distanceToTravel = Integer.parseInt(tokens[1]);
                if (fuelInput >= distanceToTravel) {
                    //we have fuel
                    fuelInput -= distanceToTravel;
                    System.out.printf("The spaceship travelled %d light-years.\n", distanceToTravel);
                } else {
                    //not enough fuel / stop the program
                    System.out.println("Mission failed.");
                    break;
                }

            } else if (commandArr[i].contains("Enemy")) {
                //Enemy 30 -  30 - enemyAmmunition
                int enemyAmmo = Integer.parseInt(tokens[1]);
                boolean fightingMode = ammunitionInput >= enemyAmmo;
                boolean runMode = ammunitionInput < enemyAmmo;
                if (fightingMode) {
                    ammunitionInput -= enemyAmmo;
                    System.out.printf("An enemy with %d armour is defeated.\n", enemyAmmo);

                } else if (runMode) {
                    //for each enemy armour point the ship consumes 2 fuel points
                    boolean isEscapePossible = fuelInput > (2 * enemyAmmo);
                    if (isEscapePossible) {
                        fuelInput -= (enemyAmmo * 2);
                        System.out.printf("An enemy with %d armour is outmaneuvered.\n", enemyAmmo);

                    } else {
                        //not fighting or running / stop the program
                        System.out.println("Mission failed.");
                        break;

                    }

                }

            } else if (commandArr[i].contains("Repair")) {
                //Repair 15 --->  15 fuel and 2x15 ammo
                int repairPoints = Integer.parseInt(tokens[1]);
                fuelInput += repairPoints;
                ammunitionInput += (repairPoints * 2);
                System.out.printf("Ammunitions added: %d.\n",repairPoints * 2);
                System.out.printf("Fuel added: %d.\n", repairPoints);

            } else if (commandArr[i].contains("Titan")) {
                System.out.println("You have reached Titan, all passengers are safe.");
            }

        }


    }
}
