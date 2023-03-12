package E06SOLID.solid.products.foods;

import theme_06_SOLID.Exersize.solid.products.Product;

public abstract class Food implements Product {

    private double grams;
    private double caloriesPer100Grams;

    protected Food(double grams, double caloriesPer100Grams) {
        this.grams = grams;
        this.caloriesPer100Grams = caloriesPer100Grams;
    }

    protected double getCaloriesPer100Grams() {
        return caloriesPer100Grams;
    }

    @Override
    public double getGrams() {
        return grams;
    }

    @Override
    public double getKilograms() {
        return getGrams() / 1000;
    }

    @Override
    public double getCalories() {
        return (this.caloriesPer100Grams / 100) * this.grams;
    }

}
