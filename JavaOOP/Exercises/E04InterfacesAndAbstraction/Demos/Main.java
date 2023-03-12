package E04InterfacesAndAbstraction.Demos;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Animal> animals = new ArrayList<>();

        Goat goat1 = new Goat("Goat 1");
        Goat goat2 = new Goat("Goat 2");
        Cow cow1 = new Cow("Cow 1");
        Cow cow2 = new Cow("Cow 2");

        animals.add(goat1);
        animals.add(goat2);
        animals.add(cow1);
        animals.add(cow2);

        for (Animal animal : animals) {
            animal.walk();
            animal.breathe();
        }

    }
}
