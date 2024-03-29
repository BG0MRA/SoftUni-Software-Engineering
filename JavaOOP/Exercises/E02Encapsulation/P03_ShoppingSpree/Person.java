package E02Encapsulation.P03_ShoppingSpree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Person {
    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {
        setName(name);
        setMoney(money);
        products = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            throw new IllegalStateException("Name cannot be empty.");
        }
    }

    private void setMoney(double money) {
        if (money >= 0) {
            this.money = money;
        } else {
            throw new IllegalStateException("Money cannot be negative");
        }
    }

    public void buyProduct(Product product) {
        if (product.getCost() <= money) {
            products.add(product);
            money -= product.getCost();

        } else {
            throw new IllegalStateException(String.format("%s can't afford %s", name, product.getName()));
        }
    }

    @Override
    public String toString() {

        String bought = "Nothing bought";

        if (!products.isEmpty()) {
            bought = products.stream()
                    .map(product -> product.getName())
                    .collect(Collectors.joining(", "));
        }

        return String.format("%s - %s", name, bought);
    }
}
