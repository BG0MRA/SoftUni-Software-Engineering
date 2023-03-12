package L05Polymorphism.P02_Shapes;

public class Rectangle extends Shape {

    private final Double height;
    private final Double width;
//    private Double perimeter;
//    private Double area;

    public Rectangle(Double height, Double width) {
        this.height = height;
        this.width = width;
        super.setPerimeter(calculatePerimeter());
        super.setArea(calculateArea());
    }

    @Override
    public Double calculatePerimeter() {
        if (getPerimeter() == null) {
            return 2 * height + 2 * width;
        }
        return getPerimeter();
    }

    @Override
    public Double calculateArea() {
        if (getArea() == null) {
            return height * width;
        }
        return getArea();
    }

    public final Double getHeight() {
        return height;
    }

    public final Double getWidth() {
        return width;
    }
}
