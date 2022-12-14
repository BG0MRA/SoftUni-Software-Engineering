package L01StacksAndQueues;

import java.util.ArrayDeque;
import java.util.Scanner;

public class P01BrowserHistory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nextNavigation = scanner.nextLine();
        String currentURL = "blank";
        ArrayDeque<String> history = new ArrayDeque<>();

        while (!nextNavigation.equals("Home")) {
            if (nextNavigation.equals("back")) {
                if (!history.isEmpty()) {
                    currentURL = history.pop();
                } else {
                    System.out.println("no previous URLs");
                    nextNavigation = scanner.nextLine();
                    continue;
                }

            } else {
                if (!currentURL.equals("blank")) {
                    history.push(currentURL);
                }

                currentURL = nextNavigation;

            }

            System.out.println(currentURL);
            nextNavigation = scanner.nextLine();
        }

    }
}

