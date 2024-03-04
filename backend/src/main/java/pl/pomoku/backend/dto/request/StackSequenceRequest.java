package pl.pomoku.backend.dto.request;

import pl.pomoku.backend.algorithm.stackSequence.Sequence;

import java.util.List;

public record StackSequenceRequest(Integer[] initArray, List<Sequence> sequences) {
}
