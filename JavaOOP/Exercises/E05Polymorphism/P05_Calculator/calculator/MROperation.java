package E05Polymorphism.P05_Calculator.calculator;

import java.util.Deque;

public class MROperation implements Operation{
    private Deque<Integer> memory;

    public MROperation(Deque<Integer> memory) {
        this.memory = memory;
    }

    @Override
    public void addOperand(int operand) {
        //we only extract from Memory Recall
    }

    @Override
    public int getResult() {
        return memory.pop();
    }

    @Override
    public boolean isCompleted() {
        return true;
    }
}
