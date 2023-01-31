package E06DefiningClasses.P07Google;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Person> peopleMap = new HashMap<>();
        String input = scanner.nextLine();

        while (!input.equals("End")) {
            String[] tokens = input.split("\\s+");
            String personName = tokens[0];
            String typeOfInfo = tokens[1];

            peopleMap.putIfAbsent(personName, new Person(personName));
            Person person = peopleMap.get(personName);
            switch (typeOfInfo) {
                case "company":
                    //•	"{Name} company {companyName} {department} {salary}"
                    String companyName = tokens[2];
                    String department = tokens[3];
                    double salary = Double.parseDouble(tokens[4]);
                    person.setCompany(new Company(companyName, department, salary));
                    break;
                case "pokemon":
                    //•	"{Name} pokemon {pokemonName} {pokemonType}"
                    String pokemonName = tokens[2];
                    String pokemonType = tokens[3];
                    person.addPokemon(new Pokemon(pokemonName, pokemonType));
                    break;
                case "parents":
                    //•	"{Name} parents {parentName} {parentBirthday}"
                    String parentName = tokens[2];
                    String parentBirthday = tokens[3];
                    person.addParent(new Parent(parentName, parentBirthday));
                    break;
                case "children":
                    //•	"{Name} children {childName} {childBirthday}"
                    String childName = tokens[2];
                    String childBirthday = tokens[3];
                    person.addChild(new Children(childName, childBirthday));
                    break;
                case "car":
                    //•	"{Name} car {carModel} {carSpeed}"
                    String carModel = tokens[2];
                    int carSpeed = Integer.parseInt(tokens[3]);
                    person.setCar(new Car(carModel, carSpeed));
                    break;
            }
            input = scanner.nextLine();
        }

        String personName = scanner.nextLine();
        peopleMap.putIfAbsent(personName, new Person(personName));

        System.out.println(peopleMap.get(personName));

    }


}

