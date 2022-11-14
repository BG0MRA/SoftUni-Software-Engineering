package E08TextProcessing;

import java.util.Scanner;

public class P02CharacterMultiplier {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        String first = input.split(" ")[0];
        String second = input.split(" ")[1];

        String forMultiply = "";
        if (first.length() > second.length()) {
            forMultiply = first.substring(0, second.length());
        } else {
            forMultiply = second.substring(0, first.length());
        }
        int sum = 0;
        for (int i = 0; i < forMultiply.length(); i++) {
            sum += (int) first.charAt(i) * (int) second.charAt(i);
        }
        if (first.length() > second.length()) {
            for (int i = forMultiply.length(); i < first.length(); i++) {
                sum += (int) first.charAt(i);
            }
        } else {
            for (int i = forMultiply.length(); i < second.length(); i++) {
                sum += (int) second.charAt(i);
            }
        }
        System.out.println(sum);
    }
}
