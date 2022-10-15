package L05Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P03MergingLists {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // You are going to receive two lists with numbers.
        // Create a result list that contains the numbers from both of the lists.
        // The first element should be from the first list, the second from the second list, and so on.
        // If the length of the two lists is not equal, just add the remaining elements at the end of the list.

        // Hints
        // Read the two lists.

        List<Integer> firstList = Arrays
                .stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> secondList = Arrays
                .stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        // Create a result list.
        List<Integer> resultList = new ArrayList<>();

        //input:
        //  3 5 2 43 12 3 54 10 23
        // 76 5 34 2 4 12

        // Start looping through them until you reach the end of the smallest one.
        int minSize = Math.min(firstList.size(), secondList.size());

        for (int i = 0; i < minSize; i++) {
            int firstNum = firstList.get(i);
            int secondNum = secondList.get(i);

            resultList.add(firstNum);
            resultList.add(secondNum);
        }
        // Finally, add the remaining elements (if any) to the end of the list.
        if(firstList.size() > secondList.size()) {
            resultList.addAll(firstList.subList(minSize, firstList.size()));
        } else if (firstList.size() < secondList.size()) {
            resultList.addAll(secondList.subList(minSize, secondList.size()));
        }

        System.out.println(resultList.toString().replaceAll("[\\[\\],]",""));
    }
}