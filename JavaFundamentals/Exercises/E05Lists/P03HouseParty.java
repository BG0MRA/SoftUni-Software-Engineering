package E05Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P03HouseParty {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Write a program that keeps track of guests going to a house party.
        //On the first input line, you are going to receive how many commands you are going to have.
        //On the next lines you are going to receive some of the following inputs:
        //• "{name} is going!"
        //• "{name} is not going!"

        int guestsNumber = Integer.parseInt(scanner.nextLine());

        List<String> guestNames = new ArrayList<>();

        for (int i = 0; i < guestsNumber; i++) {
            String lineInput = scanner.nextLine();
            String[] inputGuests = lineInput.split("\\s+");
            String name = inputGuests[0];
            if (lineInput.contains("is going!")) {
                if (guestNames.contains(name)) {
                    System.out.printf("%s is already in the list!\n", name);
                } else {
                    guestNames.add(name);
                }

            } else if (lineInput.contains("is not going!")) {
                if (guestNames.contains(name)) {
                    guestNames.remove(name);
                } else {
                    System.out.printf("%s is not in the list!\n", name);
                }
            }
        }

        for( String name : guestNames) {
            System.out.println(name);
        }
    }
}