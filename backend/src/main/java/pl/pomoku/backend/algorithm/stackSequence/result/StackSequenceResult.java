package pl.pomoku.backend.algorithm.stackSequence.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StackSequenceResult {
    private int top;
    private int maxSize;
    private int endSize;
}
