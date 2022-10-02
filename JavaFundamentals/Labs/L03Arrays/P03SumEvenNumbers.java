package L03Arrays;

import java.util.Arrays;
import java.util.Scanner;

public class P03SumEvenNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] numArr = Arrays
                .stream(scanner.nextLine().split(" "))
                .mapToInt(value -> Integer.parseInt(value))
                .toArray();


        int sum = 0;

        for( int num : numArr) {
            if (num % 2 == 0){
                sum += num;
            }
        }
        System.out.println(sum);
    }
}