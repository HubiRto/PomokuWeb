package pl.pomoku.backend.algorithm.stackSequence;

import lombok.Getter;
import pl.pomoku.backend.algorithm.stackSequence.result.StackSequenceResult;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

@Getter
public class StackSequence {
    private final Stack<Integer> stack;
    private final StackSequenceResult result;

    public StackSequence(Integer[] init) {
        this.stack = new Stack<>();
        Arrays.stream(init).forEach(this.stack::push);
        this.result = new StackSequenceResult();
        this.result.setMaxSize(stack.size());
    }

    public StackSequenceResult exec(List<Sequence> sequences) {
        for (Sequence sequence : sequences) {
            exec(sequence);
        }
        this.result.setTop(stack.lastElement());
        this.result.setEndSize(stack.size());
        return this.result;
    }

    private int exec(Sequence sequence) {
        if (sequence.getSequence() != null) {
            sequence.setNumber(exec(sequence.getSequence()));
        }
        switch (sequence.getSequenceType()) {
            case PUSH -> {
                checkMax();
                stack.push(sequence.getNumber());
            }
            case POP -> stack.pop();
            case TOP -> {
                return stack.lastElement();
            }
        }
        return -1;
    }

    private void checkMax() {
        if (this.result.getMaxSize() < this.stack.size()) this.result.setMaxSize(this.stack.size());
    }
}
