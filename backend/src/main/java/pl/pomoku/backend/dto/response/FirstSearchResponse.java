package pl.pomoku.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FirstSearchResponse {
    private List<Integer> traverse;
    private int treeHeight;
    private int maxQueueSize;
    private int internalCount;
    private int externalCount;
    private int inOperationsCount;
    private int outOperationsCount;
}
