package E03SetsAndMaps;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class P05Phonebook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> phoneBook = new HashMap<>();

        String input = scanner.nextLine();
        while (!input.equals("search")) {
            String name = input.split("-")[0];
            String phoneNumber = input.split("-")[1];
            phoneBook.put(name, phoneNumber);

            input = scanner.nextLine();
        }

        String searchedName = scanner.nextLine();
        while (!searchedName.equals("stop")) {
            if (phoneBook.containsKey(searchedName)) {
                System.out.println(searchedName + " -> " + phoneBook.get(searchedName));
            } else {
                System.out.println("Contact " + searchedName + " does not exist.");
            }

            searchedName = scanner.nextLine();
        }

    }
}
