package pl.pomoku.backend.algorithm.stackSequence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sequence {
    private Sequence sequence;
    private SequenceType sequenceType;
    private Integer number;
}
