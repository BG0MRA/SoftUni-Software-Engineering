package E06SOLID.calculators;


import E06SOLID.products.Product;

import java.util.List;

public class WeightCalculator implements ProductCalculator {

    @Override
    public double total(List<Product> products) {
        return products.stream().mapToDouble(Product::getKilograms).sum();
    }

    @Override
    public double average(List<Product> products) {
        return this.total(products) / products.size();
    }
}
