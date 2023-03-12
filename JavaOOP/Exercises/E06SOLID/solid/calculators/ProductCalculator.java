package E06SOLID.solid.calculators;

import theme_06_SOLID.Exersize.solid.products.Product;

import java.util.List;

public interface ProductCalculator {

    double total(List<Product> products);

    double average(List<Product> products);

    ;

}
