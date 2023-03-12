package E06SOLID.solid.calculators;

import theme_06_SOLID.Exersize.solid.products.Product;

import java.util.List;

public class CalorieCalculator implements ProductCalculator {

    @Override
    public double total(List<Product> products) {
        return products.stream().mapToDouble(Product::getCalories).sum();
    }

    public double average(List<Product> products) {
        return this.total(products) / products.size();
    }

}
