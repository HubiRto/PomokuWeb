package pl.pomoku.backend.algorithm.firstSearch.result;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DepthFirstSearchResult extends AbstractFirstSearchResult {
    public DepthFirstSearchResult(AbstractFirstSearchResult other) {
        super(other);
    }
}
