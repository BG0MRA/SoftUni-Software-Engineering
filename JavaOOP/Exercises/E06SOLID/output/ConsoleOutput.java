package E06SOLID.output;

public class ConsoleOutput implements Output {
    private static final String SUM = "Sum: %f";
    private static final String AVERAGE = "Average: %f";

//    private ProductCalculator calculator;

//    public ConsoleOutput(ProductCalculator calculator) {
//        this.calculator = calculator;
//    }

    @Override
    public void outputSum(double sum) {
        System.out.printf((SUM) + "%n", sum);
    }

    @Override
    public void outputAverage(double average) {
        System.out.printf((AVERAGE) + "%n", average);
    }
}
