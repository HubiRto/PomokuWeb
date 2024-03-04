package pl.pomoku.backend;

import pl.pomoku.backend.algorithm.stackSequence.Sequence;
import pl.pomoku.backend.algorithm.stackSequence.SequenceType;
import pl.pomoku.backend.algorithm.stackSequence.StackSequence;

import java.util.ArrayList;
import java.util.List;

import static pl.pomoku.backend.algorithm.stackSequence.SequenceType.*;

public class Main {
    public static void main(String[] args) {
        int[] arr = {7,4,0,17,11,8,1,10,6,14,2,13};
        StackSequence stackSequence = new StackSequence(arr);
        List<Sequence> sequences = new ArrayList<>();
        sequences.add(new Sequence(new Sequence(SequenceType.PUSH, 8), SequenceType.POP));
        sequences.add(new Sequence(TOP));
        sequences.add(new Sequence(TOP));
        sequences.add(new Sequence(new Sequence(new Sequence(TOP), PUSH), POP));
        sequences.add(new Sequence(new Sequence(PUSH, 5), POP));
        sequences.add(new Sequence(TOP));
        stackSequence.exec(sequences);

        System.out.println(stackSequence.getStack().lastElement());
    }
}
