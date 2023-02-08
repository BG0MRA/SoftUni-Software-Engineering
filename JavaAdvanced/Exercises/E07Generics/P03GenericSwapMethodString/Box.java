package E07Generics.P03GenericSwapMethodString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Box<T> {
    private List<T> values;

    public Box() {
        this.values = new ArrayList<>();
    }

    public void add(T element) {
        values.add(element);
    }

    public void swap(int firstIndex, int secondIndex) {
        checkIndex(firstIndex);
        checkIndex(secondIndex);

        T firstElement = values.get(firstIndex);
        T secondElement = values.get(secondIndex);
        values.set(firstIndex, secondElement);
        values.set(secondIndex, firstElement);

    }

    @Override
    public String toString() {

        return values.stream()
                .map(value -> String.format("%s: %s", value.getClass().getName(), value.toString()))
                .collect(Collectors.joining(System.lineSeparator()));

       /* StringBuilder stringBuilder = new StringBuilder();
        for (String value : values) {
            stringBuilder.append(String.format("%s: %s%n", value.getClass().getName(), value.toString()));
        }
        return stringBuilder.toString();*/
    }

    private void checkIndex(int index) {
        if (index < 0 || index > this.values.size() - 1) {
            throw new IndexOutOfBoundsException("Index Out of Bounds: for input " + index);
        }
    }

}

