package E05Lists;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P02ChangeList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Reads a list of integers from the console and receives commands which manipulate the list.
        List<Integer> numbersList = Arrays
                .stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        //Delete {element} - delete all elements in the array which are equal to the given element
        //Insert {element} {position} - insert element at the given position
        //You should stop the program when you receive the command "end".
        // Print all numbers in the array, separated with a single whitespace.

        String lineInput = scanner.nextLine();

        while (!lineInput.equals("end")) {
            String[] commandArr = lineInput.split("\\s+");

            if (commandArr[0].equals("Delete")) {
                int element = Integer.parseInt(commandArr[1]);
                numbersList.removeAll(Arrays.asList(element));

            } else if (commandArr[0].equals("Insert")) {
                int element = Integer.parseInt(commandArr[1]);
                int index = Integer.parseInt(commandArr[2]);
                numbersList.add(index, element);

            }

            lineInput = scanner.nextLine();
        }

        System.out.println(numbersList.toString().replaceAll("[\\[\\],]", ""));


    }
}
