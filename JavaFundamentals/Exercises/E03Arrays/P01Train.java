package E03Arrays;

import java.util.Scanner;

public class P01Train {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int wagonsCount = Integer.parseInt(scanner.nextLine());

        int[] trainArr = new int[wagonsCount];

        int peopleSum = 0;

        for (int i = 0; i < wagonsCount; i++) {
            int peopleInWagon = Integer.parseInt(scanner.nextLine());
            peopleSum += peopleInWagon;
            trainArr[i] = peopleInWagon;

        }
        for( int element : trainArr) {
            System.out.printf(element + " ");
        }
        System.out.println();
        System.out.println(peopleSum);
    }
}
