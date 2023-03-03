package L01Abstraction.P02_PointInRectangle;

public class Rectangle {
    private Point a;
    private Point c;

    public Rectangle(Point a, Point c) {
        this.a = a;
        this.c = c;
    }

    public boolean contains(Point x) {
        return x.greaterOrEqual(a) && x.lessOrEqual(c) ;
    }
}
