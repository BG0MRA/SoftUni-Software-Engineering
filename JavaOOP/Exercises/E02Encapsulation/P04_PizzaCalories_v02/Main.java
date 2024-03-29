package E02Encapsulation.P04_PizzaCalories_v02;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] pizzaInfo = scanner.nextLine().split("\\s+");
        String pizzaName = pizzaInfo[1];
        int numberOfToppings = Integer.parseInt(pizzaInfo[2]);

        String[] doughInfo = scanner.nextLine().split("\\s+");
        String flourType = doughInfo[1];
        String bakingTechnique = doughInfo[2];
        double doughWeight = Double.parseDouble(doughInfo[3]);


        try {
            Pizza pizza = new Pizza(pizzaName, numberOfToppings);
            Dough dough = new Dough(flourType, bakingTechnique, doughWeight);
            pizza.setDough(dough);

            String command = scanner.nextLine();
            while (!"END".equals(command)) {
                String[] toppingInfo = command.split("\\s+");
                String toppingType = toppingInfo[1];
                double toppingWeight = Double.parseDouble(toppingInfo[2]);
                Topping topping = new Topping(toppingType, toppingWeight);
                pizza.addTopping(topping);

                command = scanner.nextLine();
            }

            System.out.printf("%s - %.2f\n", pizza.getName(), pizza.getOverallCalories());

        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }


    }
}
