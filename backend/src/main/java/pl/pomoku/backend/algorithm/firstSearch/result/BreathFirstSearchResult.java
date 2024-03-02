package pl.pomoku.backend.algorithm.firstSearch.result;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BreathFirstSearchResult extends AbstractFirstSearchResult {
    public BreathFirstSearchResult(AbstractFirstSearchResult other) {
        super(other);
    }
}
