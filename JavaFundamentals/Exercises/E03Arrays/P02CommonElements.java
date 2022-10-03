package E03Arrays;

import java.util.Scanner;

public class P02CommonElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
//Write a program that prints common elements in two arrays.
// You have to compare the elements of the second array to the elements of the first.
        
        String[] firstArr = scanner.nextLine().split(" ");
        String[] secondArr = scanner.nextLine().split(" ");

        for (int i = 0; i < secondArr.length; i++) {

            for (int j = 0; j < firstArr.length; j++) {

                if (firstArr[j].equals(secondArr[i])) {

                    System.out.printf(secondArr[i] + " ");
                    break;
                }
                
            }

        }

    }
}
