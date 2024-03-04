package pl.pomoku.backend.algorithm.stackSequence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sequence {
    private Sequence sequence;
    private SequenceType sequenceType;
    private int number;

    public Sequence(SequenceType sequenceType, int number) {
        this.sequenceType = sequenceType;
        this.number = number;
    }

    public Sequence(Sequence sequence, SequenceType sequenceType) {
        this.sequence = sequence;
        this.sequenceType = sequenceType;
    }

    public Sequence(SequenceType sequenceType) {
        this.sequenceType = sequenceType;
    }
}
