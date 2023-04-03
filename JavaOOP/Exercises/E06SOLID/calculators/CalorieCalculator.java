package E06SOLID.calculators;



import E06SOLID.products.Product;

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
