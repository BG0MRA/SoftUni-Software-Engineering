package E06ObjectsAndClasses.P06OrderByAge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Person> personList = new ArrayList<>();
        String input = scanner.nextLine();

        while (!input.equals("End")) {
            //input -> данни за даден човек
            //input = "Desislava 1234 24".split(" ") -> ["Desislava", "1234", "24"]
            String name = input.split(" ")[0];
            String id = input.split(" ")[1];
            int age = Integer.parseInt(input.split(" ")[2]);

            Person person = new Person(name, id, age);
            personList.add(person);

            input = scanner.nextLine();
        }

        //списък с хора
        //1. сортираме по възраст (в нарастващ ред -> ascending order)
        personList.sort(Comparator.comparing(Person::getAge));
        //2. принтираме всеки човек
        for (Person person : personList) {
            //"{name} with ID: {id} is {age} years old."
            System.out.println(person);
        }
    }
}