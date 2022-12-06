package finalExam;

import java.lang.module.FindException;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class P01Hogwarts {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Spell that need to be deciphered.
        String spellCode = scanner.nextLine();

        //commands until "Abracadabra"
        String command = scanner.nextLine();

        //sample input:
        // A7ci0
        //Illusion 1 c
        //Illusion 4 o
        //Abjuration
        //Abracadabra

        while (!command.equals("Abracadabra")) {

            if (command.contains("Abjuration")) {
                //command "Abjuration"
                spellCode = spellCode.toUpperCase();
                System.out.println(spellCode);

            } else if (command.contains("Necromancy")) {
                //command "Necromancy"
                spellCode = spellCode.toLowerCase();
                System.out.println(spellCode);

            } else if (command.contains("Illusion")) {
                //command "Illusion {index} {letter}"
                //Illusion 1 c
                //replace the letter at the index with the given one and print "Done!"
                //check for invalid index
                int index = Integer.parseInt(command.split("\\s+")[1]);
                if(index < spellCode.length() && index>= 0){
                    //valid index
                    char letterToReplace = command.split("\\s+")[2].charAt(0);
                    char letterAtGivenIndex = spellCode.charAt(index);
                    spellCode = spellCode.replace(letterAtGivenIndex, letterToReplace);
                    System.out.println("Done!");
                }else {
                    System.out.println("The spell was too weak.");
                }

            } else if (command.contains("Divination")) {
                //command "Divination {first substring} {second substring}"
                //check does first string exist
                String firstSub = command.split("\\s+")[1]; //sub to replace
                String secondSub = command.split("\\s+")[2];

                if(spellCode.contains(firstSub)){
                    spellCode = spellCode.replaceAll(Pattern.quote(firstSub),secondSub);
                    System.out.println(spellCode);
                } else {
                    //skip command if not match the spell
                }

            } else if (command.contains("Alteration")) {
                //command "Alteration {substring}"
                String subToRemove = command.split("\\s+")[1];
                if (spellCode.contains(subToRemove)){
                int indexOfToRemove = spellCode.indexOf(subToRemove);
                while (indexOfToRemove != -1) {
                    spellCode = spellCode.replace(subToRemove,"");
                    indexOfToRemove = spellCode.indexOf(subToRemove);
                }
                    System.out.println(spellCode);
                } else {
                // else skip command

                }
            } else {
                //command not in the list
                System.out.println("The spell did not work!");
            }


            //update command
            command = scanner.nextLine();
        }


    }
}
