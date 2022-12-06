package finalExam;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P02EncryptingPassword {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //number > how many inputs you will receive on next line
        int n = Integer.parseInt(scanner.nextLine());

        String regex = "((.+))>(?<numbers>[0-9]+)[//|](?<lower>[a-z]+)[//|](?<upper>[A-Z]+)[//|](?<symbol>[^<>]+)<\\1$";

        Pattern pattern = Pattern.compile(regex);

        for (int i = 0; i < n; i++) {
            //read next line
            String input = scanner.nextLine();
            //match if password is correct
            Matcher matcher = pattern.matcher(input);

            //save password
            String password = "";

            if (matcher.find()) {
                //valid password
                String numbers = matcher.group("numbers");
                String lowerLetters = matcher.group("lower");
                String upperLetters = matcher.group("upper");
                String symbols = matcher.group("symbol");

                password += numbers;
                password += lowerLetters;
                password += upperLetters;
                password += symbols;
                System.out.printf("Password: %s\n", password);
            } else {
                System.out.println("Try another password!");
            }


        }

    }
}
