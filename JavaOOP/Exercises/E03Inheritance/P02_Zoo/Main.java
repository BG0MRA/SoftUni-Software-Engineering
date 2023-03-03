package E03Inheritance.P02_Zoo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Animal animal = new Animal(scanner.nextLine());
        System.out.println("Animal: " + animal.getName());

        Reptile reptile = new Reptile(scanner.nextLine());
        System.out.println("Reptile: " + reptile.getName());

        Lizard lizard = new Lizard(scanner.nextLine());
        System.out.println("Lizard: " + lizard.getName());

        Snake snake = new Snake(scanner.nextLine());
        System.out.println("Snake: " + snake.getName());

        Mammal mammal = new Mammal(scanner.nextLine());
        System.out.println("Mammal: " + mammal.getName());

        Bear bear = new Bear(scanner.nextLine());
        System.out.println("Bear: " + bear.getName());

        Gorilla gorilla = new Gorilla(scanner.nextLine());
        System.out.println("Gorilla: " + gorilla.getName());

    }
}
