package E05Polymorphism.P05_Calculator.calculator;

import java.util.Deque;

public class MSOperation implements Operation {

    private Deque<Integer> memory;
    private int result;

    public MSOperation(Deque<Integer> memory) {
        this.memory = memory;
    }

    @Override
    public void addOperand(int operand) {
        memory.push(operand);
    }

    @Override
    public int getResult() {
        return memory.peek();
    }

    @Override
    public boolean isCompleted() {
        return false;
    }
}
