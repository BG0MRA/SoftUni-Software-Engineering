package E04InterfacesAndAbstraction.P03_BirthdayCelebrations;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        List<Birthable> birthables = new ArrayList<>();
//        List<Identifiable> identifiables = new ArrayList<>();

        while (!"End".equals(input)) {
            String[] tokens = input.split("\\s+");
            String clazz = tokens[0];

            switch (clazz) {
                case "Citizen":
                    String personName = tokens[1];
                    int age = Integer.parseInt(tokens[2]);
                    String personId = tokens[3];
                    String personBirthDate = tokens[4];

                    Citizen citizen = new Citizen(personName, age, personId, personBirthDate);
                    birthables.add(citizen);
//                    identifiables.add(citizen);
                    break;
                case "Pet":
                    String petName = tokens[1];
                    String petBirthDate = tokens[2];
                    Pet pet = new Pet(petName, petBirthDate);
                    birthables.add(pet);
                    break;
//                case "Robot":
//                    String robotId = tokens[1];
//                    String model = tokens[2];
//                    Robot robot = new Robot(robotId, model);
//                    identifiables.add(robot);
//                    break;
            }

            input = scanner.nextLine();
        }

        String year = scanner.nextLine();

        StringBuilder out = new StringBuilder();

        out.append(birthables.stream()
                .map(Birthable::getBirthDate)
                .filter(birtDate -> birtDate.endsWith(year))
                .collect(Collectors.joining(System.lineSeparator())));

        String output = out.toString().length() > 0 ? out.toString() : "<no output>";

        System.out.println(output);


    }


}
