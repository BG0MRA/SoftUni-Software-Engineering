package Final.kindergarten;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Kindergarten {
    private String name;
    private int capacity;
    private List<Child> registry;

    public Kindergarten(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.registry = new ArrayList<>(capacity);
    }

    public boolean addChild(Child child) {
        if (this.registry.size() >= capacity) {
            return false;
        } else {
            this.registry.add(child);
            return true;
        }
    }

    public boolean removeChild(String firstName) {
        return this.registry.removeIf(child -> child.getFirstName().equals(firstName));
    }

    //Getter?
    public int getChildrenCount() {
        return this.registry.size();
    }

    public Child getChild(String firstName) {
        return this.registry.stream()
                .filter(child -> child.getFirstName().equals(firstName))
                .findFirst()
                .orElse(null);
    }

    public String registryReport() {
        String childReport = this.registry
                .stream()
                .sorted((child1, child2) -> {
                    if (child1.getAge() != child2.getAge()){
                        return child1.getAge() - child2.getAge();
                    }
                    if (!child1.getFirstName().equals(child2.getFirstName())) {
                        return child1.getFirstName().compareTo(child2.getFirstName());
                    }

                    return child1.getLastName().compareTo(child2.getLastName());
                })
                .map(child -> String.format("--\n%s",child.toString()))
                .collect(Collectors.joining(System.lineSeparator()));

        return String.format("Registered children in %s:\n%s", this.name, childReport);
    }
}
