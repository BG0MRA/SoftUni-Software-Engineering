package L03Arrays;

import java.util.Arrays;
import java.util.Scanner;

public class P04ReverseArrayOfStrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] stringArr = scanner.nextLine().split(" ");

        for (int i = 0; i < stringArr.length / 2; i++) {
            String firstElement = stringArr[i];
            stringArr[i] = stringArr[stringArr.length -1 - i];
            stringArr[stringArr.length -1 - i] = firstElement;
        }

        for( String element : stringArr) {
            System.out.printf(element + " ");
        }
    }
}