package L05Lists;

import java.util.*;
import java.util.stream.Collectors;

public class P05ListManipulationAdvanced {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbersList = Arrays
                .stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        //input data example:
        //12 3 123 546 222 45 7
        //Contains 3
        //Contains 121
        //Print even
        //Print odd
        //Get sum
        //Filter >= 100
        //Filter < 45
        //end


        String lineInput = scanner.nextLine();
        while (!lineInput.equals("end")) {
            String[] commandArr = lineInput.split(" ");
            String command = commandArr[0];
            switch (command) {
                case "Contains":
                    //If yes, print "Yes", otherwise, print "No such number"
                    int numberContains = Integer.parseInt(commandArr[1]);
                    boolean isContains = numbersList.contains(numberContains);
                    if (isContains) {
                        System.out.println("Yes");
                    } else {
                        System.out.println("No such number");
                    }
                    break;
                case "Print":
                    //is Even or Odd
                    boolean isEven = commandArr[1].equals("even");
                    boolean isOdd = commandArr[1].equals("odd");
                    if (isEven) {
                        for (int number : numbersList) {
                            if (number % 2 == 0) {
                                System.out.printf("%d ", number);
                            }
                        }
                    } else if (isOdd) {
                        for (int number : numbersList) {
                            if ((number % 2 == 1)) {
                                System.out.printf("%d ", number);
                            }
                        }

                    }
                    System.out.println();
                    break;
                case "Get":
                    //print the sum of all the numbers
                    if (commandArr[1].equals("sum")) {
                        int numbersSum = 0;
                        for (int number : numbersList) {
                            numbersSum += number;
                        }
                        System.out.println(numbersSum);
                    }
                    break;
                case "Filter":
                    //'<', '>', ">=", "<="
                    //Filter >= 100
                    //Filter < 45
                    int filterNumber = Integer.parseInt(commandArr[2]);
                    List<Integer> tempList = new ArrayList<>();
                    for( int number : numbersList){
                        tempList.add(number);
                    }
                    if (commandArr[1].equals("<")) {
                        tempList.removeIf(number -> number >= filterNumber);
                        System.out.println(tempList.toString().replaceAll("[\\[\\],]", ""));

                    } else if (commandArr[1].equals(">")) {
                        tempList.removeIf(number -> number <= filterNumber);
                        System.out.println(tempList.toString().replaceAll("[\\[\\],]", ""));

                    } else if (commandArr[1].equals(">=")) {
                        tempList.removeIf(number -> number < filterNumber);
                        System.out.println(tempList.toString().replaceAll("[\\[\\],]", ""));

                    } else if (commandArr[1].equals("<=")) {
                        tempList.removeIf(number -> number > filterNumber);
                        System.out.println(tempList.toString().replaceAll("[\\[\\],]", ""));

                    }
                    break;
            }
            lineInput = scanner.nextLine();
        }

    }
}