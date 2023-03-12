package L05Polymorphism.P02_Shapes;

public class Circle extends Shape {

    private final Double radius;
//    private Double perimeter;
//    private Double area;

    public Circle(Double radius) {
        this.radius = radius;
        super.setPerimeter(calculatePerimeter());
        super.setArea(calculateArea());
    }

    @Override
    public Double calculatePerimeter() {
        if (getPerimeter() == null) {
            return 2 * Math.PI * radius;
        }

        return getPerimeter();
    }

    @Override
    public Double calculateArea() {
        if (getArea() == null) {
            return Math.PI * radius * radius;
        }
        return getArea();
    }

    public final Double getRadius() {
        return radius;
    }
}
