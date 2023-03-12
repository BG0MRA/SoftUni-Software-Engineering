package E04InterfacesAndAbstraction.P07_CollectionHierarchy_v02;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] items = scanner.nextLine().split("\\s+");

        int removingCounter = Integer.parseInt(scanner.nextLine());

        AddCollection firstCollection = new AddCollection();
        AddRemoveCollection secondCollection = new AddRemoveCollection();
        MyListImpl thirdCollection = new MyListImpl();

        performAddOperations(items, firstCollection);
        performAddOperations(items, secondCollection);
        performAddOperations(items, thirdCollection);

        performRemoveOperations(removingCounter,secondCollection);
        performRemoveOperations(removingCounter,thirdCollection);
    }

    private static void performRemoveOperations(int removingCounter, AddRemovable collection) {
        StringBuilder out = new StringBuilder();
        while (removingCounter-- > 0) {
            out.append(collection.remove()).append(" ");
        }
        System.out.println(out.toString().trim());
    }

    private static void performAddOperations(String[] items, Addable collection) {
        StringBuilder out = new StringBuilder();
        for (String item : items) {
            out.append(collection.add(item)).append(" ");
        }
        System.out.println(out.toString().trim());
    }
}

