package E01Abstraction.Demos;

public class Sum {
    static {
        System.out.println("Hello I'm Sum static block");
    }
    public static int calculate(int a, int b) {
        return a + b;
    }
}
