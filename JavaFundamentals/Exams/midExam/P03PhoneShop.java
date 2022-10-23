package midExam;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P03PhoneShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //firstLine - list of phones separated by ", " (comma and space)
        //nextLine - commands until you receive "End"

        //SamsungA50, MotorolaG5, IphoneSE
        List<String> inputPhoneLists = Arrays
                .stream(scanner.nextLine().split(", "))
                .collect(Collectors.toList());

        //command received:
        String command = scanner.nextLine();

        while (!command.equals("End")) {
            String[] commandArr = command.split(" - ");

            switch (commandArr[0]) {
                case "Add":
                    //Add - Iphone10
                    String phoneToAdd = commandArr[1];

                    if (!inputPhoneLists.contains(phoneToAdd)) {
                        inputPhoneLists.add(phoneToAdd);
                    }

                    break;
                case "Remove":
                    //Remove - IphoneSE
                    String phoneToRemove = commandArr[1];

                    if (inputPhoneLists.contains(phoneToRemove)) {
                        inputPhoneLists.remove(phoneToRemove);
                    }
                    break;
                case "Bonus phone":
                    //Bonus phone - XiaomiNote:Iphone5 {oldPhone}:{newPhone}
                    String[] parsePhones = commandArr[1].split(":");
                    String oldPhone = parsePhones[0];
                    String newPhone = parsePhones[1];

                    if(inputPhoneLists.contains(oldPhone)){
                        //add the new phone after the old one
                        inputPhoneLists.add(inputPhoneLists.indexOf(oldPhone) + 1, newPhone);
                    }
                    break;
                case "Last":
                    //Last - SamsungA50
                    String lastPhone = commandArr[1];
                    if (inputPhoneLists.contains(lastPhone)) {
                        //change its position and put it last in your storage.
                        inputPhoneLists.remove(lastPhone);
                        inputPhoneLists.add(lastPhone);

                        break;
                    }
            }

            //updating the command
            command = scanner.nextLine();
        }

        //After receiving "End" command, print the phones in your storage, separated by ", " (comma and space)
        System.out.println(inputPhoneLists.toString().replaceAll("[\\[\\]]", ""));

    }
}
