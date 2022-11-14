package E08TextProcessing;

import java.util.Scanner;

public class P01ValidUsernames {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] namesArr = scanner.nextLine().split(", ");
        for (int i = 0; i < namesArr.length; i++) {
            int count = 0;
            if (namesArr[i].length() >= 3 && namesArr[i].length() <= 16) {
                for (char symbol : namesArr[i].toCharArray()) {
                    if (Character.isLetterOrDigit(symbol) ||
                            symbol == '-' || symbol == '_') {
                        count++;
                    }
                }
            }
            if (count == namesArr[i].length())
                System.out.println(namesArr[i]);
        }
    }
}
