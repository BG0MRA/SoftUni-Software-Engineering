package E01StacksAndQueues;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P10Robotics {
    public static List<Robot> robotsList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] robotsInputData = scanner.nextLine().split("[-;]");
        String[] startingTime = scanner.nextLine().split(":");
        int hour = Integer.parseInt(startingTime[0]);
        int minute = Integer.parseInt(startingTime[1]);
        int second = Integer.parseInt(startingTime[2]);

        long currentTimeInSeconds = hour * 3600 + minute * 60 + second;


        for (int i = 0; i < robotsInputData.length; i += 2) {
            String robotName = robotsInputData[i];
            int robotProcessTime = Integer.parseInt(robotsInputData[i + 1]);
            Robot currentRobot = new Robot(robotName, robotProcessTime, currentTimeInSeconds);
            robotsList.add(currentRobot);
        }

        ArrayDeque<String> productsQueue = new ArrayDeque<>();
        String product = scanner.nextLine();
        while (!product.equals("End")) {
            productsQueue.offer(product);
            product = scanner.nextLine();
        }

        while (!productsQueue.isEmpty()) {
            currentTimeInSeconds++;
            String currentProduct = productsQueue.poll();
            int index = getNextAvailableRobotIndex(currentTimeInSeconds);
            if (index != -1) {
                //robot star to work
                Robot nextAvailableRobot = robotsList.get(index);
                String robotName = nextAvailableRobot.getName();
                int robotProcessTime = nextAvailableRobot.getProcessTime();
                long timeWhenWillBeFreeAgain = currentTimeInSeconds + robotProcessTime;
                nextAvailableRobot.setTimeWhenIsFree(timeWhenWillBeFreeAgain);
                robotsList.set(index, nextAvailableRobot);
                System.out.printf("%s - %s [%s]\n", robotName, currentProduct, getTimeFromSeconds(currentTimeInSeconds));

            } else {
                productsQueue.offer(currentProduct);
            }
        }


    }

    private static int getNextAvailableRobotIndex(long currentTimeInSeconds) {
        int index = -1;
        for (int i = 0; i < robotsList.size(); i++) {
            if (robotsList.get(i).getTimeWhenIsFree() <= currentTimeInSeconds) {
                index = i;
                break;
            }
        }

        return index;
    }

    private static String getTimeFromSeconds(long seconds) {
        long hours = (seconds / 3600) % 24;
        long minutes = (seconds / 60) % 60;
        seconds = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public static class Robot {
        private String name;
        private int processTime;
        private long timeWhenIsFree;

        public Robot(String name, int processTime, long timeWhenIsFree) {
            this.name = name;
            this.processTime = processTime;
            this.timeWhenIsFree = timeWhenIsFree;
        }

        public void setTimeWhenIsFree(long timeWhenIsFree) {
            this.timeWhenIsFree = timeWhenIsFree;
        }

        public String getName() {
            return this.name;
        }

        public int getProcessTime() {
            return this.processTime;
        }

        public long getTimeWhenIsFree() {
            return this.timeWhenIsFree;
        }
    }
}
