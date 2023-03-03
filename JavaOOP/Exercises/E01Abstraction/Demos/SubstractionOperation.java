package E01Abstraction.Demos;

public class SubstractionOperation implements Operation {
    @Override
    public int calculate(int a, int b) {
        return a - b;
    }
}
