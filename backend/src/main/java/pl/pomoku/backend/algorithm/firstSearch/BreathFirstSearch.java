package pl.pomoku.backend.algorithm.firstSearch;

import pl.pomoku.backend.algorithm.firstSearch.result.AbstractFirstSearchResult;
import pl.pomoku.backend.algorithm.firstSearch.result.BreathFirstSearchResult;

import java.util.LinkedList;

public class BreathFirstSearch extends AbstractFirstSearch<BreathFirstSearchResult> {
    public BreathFirstSearch(int[][] graph, int startIndex) {
        super(graph, startIndex);
        super.collection = new LinkedList<>();
    }

    @Override
    protected BreathFirstSearchResult buildResult(AbstractFirstSearchResult result) {
        return new BreathFirstSearchResult(result);
    }
}
