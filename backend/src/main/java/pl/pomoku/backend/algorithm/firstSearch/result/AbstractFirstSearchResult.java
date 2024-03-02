package pl.pomoku.backend.algorithm.firstSearch.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AbstractFirstSearchResult {
    private int treeHeight;
    private int maxQueueSize;
    private int internalCount;
    private int externalCount;
    private int inOperationsCount;
    private int outOperationsCount;
    private List<Integer> traverse = new ArrayList<>();

    public AbstractFirstSearchResult(AbstractFirstSearchResult other) {
        this.treeHeight = other.treeHeight;
        this.maxQueueSize = other.maxQueueSize;
        this.internalCount = other.internalCount;
        this.externalCount = other.externalCount;
        this.inOperationsCount = other.inOperationsCount;
        this.outOperationsCount = other.outOperationsCount;
        this.traverse = new ArrayList<>(other.traverse);
    }
}
