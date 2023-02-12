package Prep.JA_22102022.shelter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Shelter {
    private int capacity;
    private List<Animal> data;

    public Shelter(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>(capacity);

    }

    public void add(Animal animal) {
        if (data.size() < capacity) {
            data.add(animal);
        }
    }

    public boolean remove(String name) {
        for (Animal animal : data) {
            if (animal.getName().equals(name)) {
                data.remove(animal);
                return true;
            }
        }
        return false;
//        return data.removeIf(a -> a.getName().equals(name));
    }

    public Animal getAnimal(String name, String caretaker) {
        for (Animal animal : data) {
            if (animal.getName().equals(name) && animal.getCaretaker().equals(caretaker)) {
                return animal;
            }
        }
        return null;
//        return data.stream()
//                .filter(a -> a.getName().equals(name) && a.getCaretaker().equals(caretaker))
//                .findFirst()
//                .orElse(null);
    }

    public Animal getOldestAnimal() {
        return this.data
                .stream()
                .sorted((a1, a2) -> a2.getAge() - a1.getAge())
                .collect(Collectors.toList()).get(0);

//        return data.stream()
//                .max(Comparator.comparingInt(Animal::getAge))
//                .orElse(null);
    }

    public int getCount() {
        return this.data.size();
    }

    public String getStatistics() {
        String animalPrint = this.data
                .stream()
                .map(a -> String.format("%s %s", a.getName(), a.getCaretaker()))
                .collect(Collectors.joining(System.lineSeparator()));

        return String.format("The shelter has the following animals:\n%s", animalPrint);

//        StringBuilder statistics = new StringBuilder();
//        statistics.append("The shelter has the following animals:");
//        statistics.append(System.lineSeparator());
//        statistics.append(data.stream()
//                .map(a -> String.format("%s %s", a.getName(), a.getCaretaker()))
//                .collect(Collectors.joining(System.lineSeparator())));
//
//        return statistics.toString();

    }
}
