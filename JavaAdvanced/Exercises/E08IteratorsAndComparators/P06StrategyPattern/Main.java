package E08IteratorsAndComparators.P06StrategyPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfPeople = Integer.parseInt(scanner.nextLine());
        TreeSet<Person> nameSet = new TreeSet<>(new NameComparator());
        TreeSet<Person> ageSet = new TreeSet<>(new AgeComparator());

        while (numberOfPeople-- > 0) {
            String[] personData = scanner.nextLine().split("\\s+");
            String name = personData[0];
            int age = Integer.parseInt(personData[1]);
            Person person = new Person(name, age);
            nameSet.add(person);
            ageSet.add(person);
        }


        System.out.println(nameSet.stream()
                .map(person -> person.toString())
                .collect(Collectors.joining(System.lineSeparator())));

        System.out.println(ageSet.stream()
                .map(person -> person.toString())
                .collect(Collectors.joining(System.lineSeparator())));


    }
}