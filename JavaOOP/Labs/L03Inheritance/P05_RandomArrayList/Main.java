package L03Inheritance.P05_RandomArrayList;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        RandomArrayList<Integer> randomArrayList = new RandomArrayList<>();

        randomArrayList.add(13);
        randomArrayList.add(42);
        randomArrayList.add(73);
        randomArrayList.add(69);

        System.out.println(randomArrayList.getRandomElement());


    }
}
