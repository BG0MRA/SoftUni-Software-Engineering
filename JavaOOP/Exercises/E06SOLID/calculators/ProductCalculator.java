package E06SOLID.calculators;


import E06SOLID.products.Product;

import java.util.List;

public interface ProductCalculator {

    double total(List<Product> products);

    double average(List<Product> products);

    ;

}
