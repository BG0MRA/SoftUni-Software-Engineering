package E02DataTypesAndVariables;

import java.util.Scanner;

public class P03Elevator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //The input holds two lines:
        //the number of people n and the capacity p of the elevator.

        int people = Integer.parseInt(scanner.nextLine());
        int capacity = Integer.parseInt(scanner.nextLine());

        int courses = 0;

      while (people > 0) {
          people = people - capacity;
          courses++;

      }
        System.out.println(courses);
    }

}