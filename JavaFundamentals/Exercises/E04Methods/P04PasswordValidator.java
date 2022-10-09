package E04Methods;

import java.util.Scanner;

public class P04PasswordValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String password = scanner.nextLine().toLowerCase();

        passwordValidation(password);
        //Write a program that checks if a given password is valid. Password rules are:
        //    • 6 – 10 characters (inclusive);
        //    • Consists only of letters and digits;
        //    • Have at least 2 digits.

        //If a password is valid, print "Password is valid".
        // If it is not valid, for every unfulfilled rule, print a message:
        //    • "Password must be between 6 and 10 characters";
        //    • "Password must consist only of letters and digits";
        //    • "Password must have at least 2 digits".
    }

    private static void passwordValidation(String password) {
        boolean isLengthValid = false;
        boolean isConsistLettersAndDigitsValid = false;
        boolean isLongEnoughValid = false;

        if (!isPasswordLengthValid(password)) {
            System.out.println("Password must be between 6 and 10 characters");
        } else {
            isLengthValid = true;
        }
        if (!isPasswordConsistLettersAndDigits(password)) {
            System.out.println("Password must consist only of letters and digits");
        } else {
            isConsistLettersAndDigitsValid = true;
        }
        if (!isPasswordLongEnough(password)) {
            System.out.println("Password must have at least 2 digits");
        } else {
            isLongEnoughValid = true;
        }
        if (isLengthValid && isLongEnoughValid && isConsistLettersAndDigitsValid) {
            System.out.println("Password is valid");
        }

    }

    private static boolean isPasswordLongEnough(String password) {
        int countDigitsInPassword = 0;
        for (char character : password.toCharArray()) {
            if (character >= '0' && character <= '9') {
                countDigitsInPassword++;
            }
        }
        if (countDigitsInPassword >= 2) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isPasswordLengthValid(String password) {
        if (password.length() >= 6 && password.length() <= 10) {
            return true;
        } else {
            return false;
        }

    }

    private static boolean isPasswordConsistLettersAndDigits(String password) {
        boolean isCharacterValid = false;
        for (char character : password.toCharArray()) {
            if (character >= 'a' && character <= 'z') {
                isCharacterValid = true;
            } else if (character >= '0' && character <= '9') {
                isCharacterValid = true;
            } else {
                isCharacterValid = false;
                break;
                //if ony one difference is found break!
            }
        }
        return isCharacterValid;
    }

}