package pl.pomoku.backend.algorithm.stackSequence;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

@Getter
public class StackSequence {
    private final Stack<Integer> stack;

    public StackSequence(int[] init) {
        this.stack = new Stack<>();
        Arrays.stream(init).forEach(this.stack::push);
    }

    public void exec(List<Sequence> sequences) {
        for(Sequence sequence : sequences) {
            exec(sequence);
        }
    }
    private int exec(Sequence sequence) {
        if(sequence.getSequence() != null) {
            sequence.setNumber(exec(sequence.getSequence()));
        }
        switch (sequence.getSequenceType()) {
            case PUSH -> stack.push(sequence.getNumber());
            case POP -> stack.pop();
            case TOP -> {
                return stack.lastElement();
            }
        }
        return -1;
    }
}
