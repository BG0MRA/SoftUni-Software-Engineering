package E06DefiningClasses.P05CarSalesman;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfEngines = Integer.parseInt(scanner.nextLine());
        Map<String, Engine> enginesMap = new HashMap<>();

        while (numberOfEngines-- > 0) {
            String[] engineInput = scanner.nextLine().split("\\s+");
            String model = engineInput[0];
            Engine engine = new Engine(engineInput);
            enginesMap.put(model, engine);
        }

        int numberOfCars = Integer.parseInt(scanner.nextLine());

        List<Car> carsList = new ArrayList<>();

        while (numberOfCars-- > 0) {
            String[] carInput = scanner.nextLine().split("\\s+");

            Car car = new Car(carInput, enginesMap);
            carsList.add(car);
        }

        carsList.forEach(System.out::println);

    }
}
