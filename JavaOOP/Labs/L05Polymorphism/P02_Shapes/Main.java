package L05Polymorphism.P02_Shapes;

public class Main {
    public static void main(String[] args) {

        Shape rectangle = new Rectangle(2D, 4D);
        Shape circle = new Circle(13.4);

        printShape(rectangle);
        printShape(circle);

        }

    private static void printShape (Shape shape ) {
        System.out.println("Perimeter: "+shape.calculatePerimeter());
        System.out.println("Area: "+shape.calculateArea());
    }
}
