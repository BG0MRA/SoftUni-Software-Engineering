package E07Generics.P02GenericBoxOfInteger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Box <T> {
    private List<T> values;

    public Box() {
        this.values = new ArrayList<>();
    }

    public void add(T element) {
        values.add(element);
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
}