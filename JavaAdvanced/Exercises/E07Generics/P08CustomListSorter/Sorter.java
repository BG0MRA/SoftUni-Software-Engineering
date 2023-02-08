package E07Generics.P08CustomListSorter;

public class Sorter {

    public static <T extends Comparable<T>> void sort(CustomList<T> list) {

        //5  2   3   1
        //2  5   3   1
        //2  3   5   1
        //2  3   1   5

        //2  1   3   5

        //1  2   3   5

        //bubble sort
        for (int i = 0; i < list.size(); i++) {
            T current = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                if (current.compareTo(list.get(j)) > 0) {
                    list.swap(i, j);
                }
            }
        }
    }


}