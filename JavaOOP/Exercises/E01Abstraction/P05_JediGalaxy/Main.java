package E01Abstraction.P05_JediGalaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = readPositions(scanner.nextLine());
        int rows = dimensions[0];
        int cols = dimensions[1];

        Galaxy galaxy = new Galaxy(new Field(rows, cols));

        String command = scanner.nextLine();
        long starsCollected = 0;

        while (!command.equals("Let the Force be with you")) {
            int[] jediPosition = readPositions(command);
            int[] evilPosition = readPositions(scanner.nextLine());

            int rowEvil = evilPosition[0];
            int colEvil = evilPosition[1];

            galaxy.moveEvil(rowEvil, colEvil);

            int rowJedi = jediPosition[0];
            int colJedi = jediPosition[1];

            starsCollected += galaxy.moveJedi(rowJedi, colJedi);

            command = scanner.nextLine();

        }

        System.out.println(starsCollected);

    }

    public static int[] readPositions(String input) {
        return Arrays.stream(input.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

}
