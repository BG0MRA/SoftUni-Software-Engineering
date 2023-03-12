package E04InterfacesAndAbstraction.P07_CollectionHierarchy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] strings = scanner.nextLine().split("\\s+");

        int removingCounter = Integer.parseInt(scanner.nextLine());

        AddCollection firstCollection = new AddCollection();
        AddRemoveCollection secondCollection = new AddRemoveCollection();
        MyListImpl thirdCollection = new MyListImpl();

        List<Collection> collections = new ArrayList<>();
        collections.add(firstCollection);
        collections.add(secondCollection);
        collections.add(thirdCollection);

        collections.stream()
                .map(collection -> addStringsToCollection(strings, collection))
                .forEach(System.out::println);

        System.out.println(removeCountElementsFromCollection(removingCounter, secondCollection));
        System.out.println(removeCountElementsFromCollection(removingCounter, thirdCollection));
    }

    private static String removeCountElementsFromCollection(int removingCounter, Collection collection) {
        StringBuilder out = new StringBuilder();
        while (removingCounter-- > 0) {
            out.append(collection.remove()).append(" ");
        }
        return out.toString().trim();
    }

    private static String addStringsToCollection(String[] strings, Collection collection) {
        StringBuilder out = new StringBuilder();
        for (String string : strings) {
            out.append(collection.add(string)).append(" ");
        }
        return out.toString().trim();
    }
}

