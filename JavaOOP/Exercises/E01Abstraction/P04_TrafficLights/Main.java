package E01Abstraction.P04_TrafficLights;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<TrafficLight> trafficLights = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Color::valueOf)
                .map(TrafficLight::new)
                .collect(Collectors.toList());

        int n = Integer.parseInt(scanner.nextLine());

        while (n-- > 0) {

            trafficLights.forEach(TrafficLight::changeColor);

            System.out.println(trafficLights.stream().map(t -> t.getCurrentColor().name())
                    .collect(Collectors.joining(" ")));
        }
    }
}
