package pl.pomoku.backend.algorithm.firstSearch;

import pl.pomoku.backend.algorithm.firstSearch.result.AbstractFirstSearchResult;
import pl.pomoku.backend.algorithm.firstSearch.result.DepthFirstSearchResult;

import java.util.ArrayDeque;

public class DepthFirstSearch extends AbstractFirstSearch<DepthFirstSearchResult> {
    public DepthFirstSearch(int[][] graph, int startIndex) {
        super(graph, startIndex);
        super.collection = new ArrayDeque<>();
    }

    @Override
    protected DepthFirstSearchResult buildResult(AbstractFirstSearchResult result) {
        return new DepthFirstSearchResult(result);
    }
}
