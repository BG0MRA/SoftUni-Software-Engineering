package E06SOLID.solid.products.foods;

public class Chips extends Food {

    public static final double CALORIES_PER_100_GRAMS = 529.0;

    private double grams;

    public Chips(double grams) {
        super(grams, CALORIES_PER_100_GRAMS);
    }
}
