package L10TestDrivenDevelopment.src.main.java;

import jdk.jshell.spi.ExecutionControl;

import java.util.*;
import java.util.stream.Collectors;

public class Instock implements ProductStock {

    private List<Product> products;

    public Instock() {
        this.products = new ArrayList<>();
    }

    @Override
    public void add(Product product) {
        this.products.add(product);
    }

    @Override
    public boolean contains(Product product) {
        return this.products.stream()
                .anyMatch(p -> p.getLabel().equals(product.getLabel()));
    }

    @Override
    public int getCount() {
        return this.products.size();
    }

    @Override
    public Product find(int index) {
        return products.get(index);
    }

    @Override
    public void changeQuantity(String productLabel, int quantity) {
        Product product = findByLabel(productLabel);
        product.setQuantity(quantity);
    }

    @Override
    public Product findByLabel(String label) {
        return products.stream()
                .filter(p -> p.getLabel().equals(label))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unable to find product with label " + label));
    }

    @Override
    public Iterable<Product> findFirstByAlphabeticalOrder(int count) {
        if (count > products.size() || count <= 0) {
            return new ArrayList<>();
        }

        return products.stream()
                .sorted(Comparator.comparing(Product::getLabel))
                .limit(count)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllInRange(double lo, double hi) {
        return products.stream()
                .filter(p -> p.getPrice() > lo && p.getPrice() <= hi)
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllByPrice(double price) {
        return products.stream()
                .filter(p -> p.getPrice() == price)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findFirstMostExpensiveProducts(int count) {
        if (count > products.size() || count <= 0) {
            throw new IllegalArgumentException("Not enough products in stock we have "
                    + products.size() + " but requested were " + count);
        }

        return products.stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .limit(count)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllByQuantity(int quantity) {
        return products.stream()
                .filter(p -> p.getQuantity() == quantity)
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Product> iterator() {
        return this.products.iterator();
    }
}
