package E02Encapsulation.P04_PizzaCalories_v02;

public enum ToppingType {
    //•	Meat – 1.2;
    //•	Veggies – 0.8;
    //•	Cheese – 1.1;
    //•	Sauce – 0.9;

    Meat (1.2),
    Veggies (0.8),
    Cheese (1.1),
    Sauce (0.9);

    private double modifier;

    ToppingType(double modifier) {
        this.modifier = modifier;
    }

    public double getModifier() {
        return modifier;
    }
}
