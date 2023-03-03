package E02Encapsulation.P04_PizzaCalories_v02;

import java.util.Arrays;

public class Topping {
    private ToppingType toppingType;
    private double weight;

    public Topping(String toppingType, double weight) {
        setToppingType(toppingType);
        setWeight(weight);
    }

    private void setToppingType(String toppingType) {

        boolean isNotExist = Arrays.stream(ToppingType.values())
                .noneMatch(e -> e.name().equals(toppingType));

        if (isNotExist) {
            throw new IllegalStateException(String.format(
                    "Cannot place %s on top of your pizza.", toppingType));
        }

        this.toppingType = ToppingType.valueOf(toppingType);

    }

    private void setWeight(double weight) {
        if (weight >= 1 && weight <= 50) {
            this.weight = weight;
        } else {
            throw new IllegalStateException(String.format(
                    "%s weight should be in the range [1..50].", toppingType));
        }
    }

    public double calculateCalories() {
        return 2 * weight * toppingType.getModifier();
    }
}
