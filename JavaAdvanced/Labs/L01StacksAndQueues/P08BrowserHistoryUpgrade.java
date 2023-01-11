package L01StacksAndQueues;

import java.util.ArrayDeque;
import java.util.Scanner;

public class P08BrowserHistoryUpgrade {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<String> historyBack = new ArrayDeque<>();
        ArrayDeque<String> historyForward = new ArrayDeque<>();
        String currentURL = "blank";

        String nextNavigation = scanner.nextLine();
        while (!nextNavigation.equals("Home")) {

            if (nextNavigation.equals("back")) {
                if (historyBack.isEmpty()) {
                    System.out.println("no previous URLs");
                } else {
                    historyForward.push(currentURL);
                    currentURL = historyBack.pop();
                    System.out.println(currentURL);
                }

            } else if (nextNavigation.equals("forward")) {
                if (historyForward.isEmpty()) {
                    System.out.println("no next URLs");
                } else {
                    historyBack.push(currentURL);
                    currentURL = historyForward.pop();
                    System.out.println(currentURL);
                }

            } else {
                if (!currentURL.equals("blank")) {
                    historyBack.push(currentURL);
                }
                historyForward.clear();
                currentURL = nextNavigation;
                System.out.println(currentURL);
            }

            nextNavigation = scanner.nextLine();
        }

    }
}
