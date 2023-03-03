package E02Encapsulation.P03_ShoppingSpree;

public class Product {
    private String name;
    private double cost;

    public Product(String name, double cost) {
        setName(name);
        setCost(cost);
    }

    private void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            throw new IllegalStateException("Name cannot be empty");
        }
    }

    private void setCost(double cost) {
        if (cost >= 0) {
            this.cost = cost;
        } else {
            throw new IllegalStateException("Money cannot be negative");
        }

    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }
}
