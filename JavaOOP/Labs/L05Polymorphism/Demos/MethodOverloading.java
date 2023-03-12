package L05Polymorphism.Demos;

public class MethodOverloading {

    public static void main(String[] args) {
        sum(3.14, 13);
        sum(13, 3.14);

        //will call double, double method
        sum(3.14, 3.14);

        //will call Double, Double method
        sum(Double.valueOf(3.14), Double.valueOf(3.14));

    }

    //Method Overloading

    public static double sum(int f, double s) {
        return f + s;
    }

    public static double sum(double f, int s) {
        return f + s;
    }

    public static double sum(double f, double s) {
        return f + s;
    }

    public static double sum(Double f, Double s) {
        return f + s;
    }
}
