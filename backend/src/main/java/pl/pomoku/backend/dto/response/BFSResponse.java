package pl.pomoku.backend.dto.response;

import java.util.List;

public record BFSResponse(
        List<Integer> traverse,
        int treeHeight,

        int maxQueueSize,

        int internalCount,

        int externalCount,

        int inOperationsCount,

        int outOperationsCount
) {
}
