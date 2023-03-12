package E05Polymorphism.P03_WildFarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Animal> animals = new ArrayList<>();
        List<Food> foods = new ArrayList<>();

        String input = scanner.nextLine();

        while (!"End".equals(input)) {
            Animal animal = createAnimal(input);
            animals.add(animal);

            Food food = createFood(scanner.nextLine());
            foods.add(food);

            input = scanner.nextLine();
        }

        for (int i = 0; i < animals.size(); i++) {
            Animal animal = animals.get(i);
            Food food = foods.get(i);

            animal.makeSound();
            animal.eat(food);
        }

        animals.forEach(System.out::println);

    }

    private static Food createFood(String input) {
        String foodTokens[] = input.split("\\s+");
        String foodType = foodTokens[0];
        int quantity = Integer.parseInt(foodTokens[1]);

        switch (foodType) {
            case "Meat":
                return new Meat(quantity);
            case "Vegetable":
                return new Vegetable(quantity);
            default:
                throw new IllegalArgumentException("Invalid type of Food");
        }
    }

    private static Animal createAnimal(String input) {
        String animalTokens[] = input.split("\\s+");
        String animalType = animalTokens[0];
        String animalName = animalTokens[1];
        double animalWeight = Double.parseDouble(animalTokens[2]);
        String livingRegion = animalTokens[3];

        switch (animalType) {
            case "Mouse":
                return new Mouse(animalName, animalWeight, livingRegion);
            case "Zebra":
                return new Zebra(animalName, animalWeight, livingRegion);
            case "Tiger":
                return new Tiger(animalName, animalWeight, livingRegion);
            case "Cat":
                String breed = animalTokens[4];
                return new Cat(animalName, animalWeight, livingRegion, breed);
            default:
                throw new IllegalArgumentException("Invalid type of Animal");
        }


    }
}
