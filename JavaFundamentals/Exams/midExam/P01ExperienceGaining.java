package midExam;

import java.text.DecimalFormat;
import java.util.Scanner;

public class P01ExperienceGaining {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //first line, you will receive needed experience amount - real number [0.0  .. 4000000.0]
        //second line, you will receive the count of battles - an integer number in range [0 .. 500]
        //on following lines you will receive the experience earned per battle - a real number [ 0.0 .. 5000.0]

        //calculate if he can unlock the tank:
        //he gets 15% more experience for every third battle
        //and 10% less for every fifth battle, and 5% more on every fifteenth.
        //You also need to stop the program as soon as he collect the needed experience.

        double experienceForLevel = Double.parseDouble(scanner.nextLine());
        int totalBattles = Integer.parseInt(scanner.nextLine());


        boolean isLevelUp = false;
        int countBattles = 0;
        double experienceGained = 0;
        for (int i = 1; i <= totalBattles; i++) {
            double experiencePerBattle = Double.parseDouble(scanner.nextLine());
            if (experienceGained < experienceForLevel) {
                if (i % 3 == 0) {
                    if (i % 15 == 0) {
                        double experience15 = experiencePerBattle * 0.15;
                        double experience5 = experiencePerBattle * 0.05;
                        double experienceNegative10 = experiencePerBattle * 0.10;
                        experiencePerBattle = (experiencePerBattle + experience15) + (experiencePerBattle + experience5) - experienceNegative10;

                    } else {
                        experiencePerBattle = experiencePerBattle * 1.15;
                    }
                } else if (i % 5 == 0) {
                    experiencePerBattle = experiencePerBattle * 0.90;
                }
                experienceGained += experiencePerBattle;
            }
            if (experienceGained >= experienceForLevel) {
                countBattles = i;
                isLevelUp = true;
                break;
            }
        }

        if (isLevelUp) {
            System.out.printf("Player successfully collected his needed experience for %d battles.", countBattles);
        } else {
            System.out.printf("Player was not able to collect the needed experience, %.2f more needed.", experienceForLevel - experienceGained);
        }


    }
}
