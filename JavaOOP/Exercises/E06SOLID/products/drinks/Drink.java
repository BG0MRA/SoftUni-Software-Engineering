package E06SOLID.products.drinks;

public abstract class Drink implements IDrink {

    private double milliliters;
    private double caloriesPer100Grams;
    private double density;

    protected Drink(double milliliters, double caloriesPer100Grams, double density) {
        this.milliliters = milliliters;
        this.caloriesPer100Grams = caloriesPer100Grams;
        this.density = density;
    }

    protected double getCaloriesPer100Grams() {
        return caloriesPer100Grams;
    }

    protected double getDensity() {
        return density;
    }

    @Override
    public double getMilliliters() {
        return milliliters;
    }

    @Override
    public double getGrams() {
        return getMilliliters() * this.density;
    }

    @Override
    public double getLitres() {
        return getMilliliters() / 1000;
    }

    @Override
    public double getKilograms() {
        return getGrams() / 1000;
    }

    @Override
    public double getCalories() {
        return (this.caloriesPer100Grams / 100) * this.getGrams();
    }
}
