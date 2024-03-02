package pl.pomoku.backend.algorithm.firstSearch;

import pl.pomoku.backend.algorithm.firstSearch.result.AbstractFirstSearchResult;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;

public abstract class AbstractFirstSearch<R extends AbstractFirstSearchResult> {
    protected Deque<Vertex> collection;
    protected Vertex startVertex;

    private static int maxQueueSize = 0;
    private static int treeHeight = 0;
    private static int internalCount = 0;
    private static int externalCount = 0;
    private static int outOperationsCount = 0;
    private static int inOperationsCount = 0;
    private static int visitOrder = 0;

    public AbstractFirstSearch(int[][] graph, int startIndex) {
        List<Vertex> vertices = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            vertices.add(new Vertex(i));
        }

        for (int x = 0; x < graph.length; x++) {
            List<Vertex> neighbors = new ArrayList<>();
            for (int y = 0; y < graph[x].length; y++) {
                int neighborIndex = graph[x][y];
                neighbors.add(vertices.get(neighborIndex));
            }
            vertices.get(x).setNeighbors(neighbors);
        }
        this.startVertex = vertices.get(startIndex);
    }

    @SuppressWarnings("unchecked")
    public R run() {
        List<Integer> traverse = new ArrayList<>();
        startVertex.setVisited(true);
        startVertex.setLevel(0);
        traverse.add(startVertex.getData());
        collection.add(startVertex);
        inOperationsCount++;
        if (maxQueueSize < collection.size()) maxQueueSize = collection.size();

        while (!collection.isEmpty()) {
            Vertex current = collection.poll();

            int currentLevel = current.getLevel();
            treeHeight = Math.max(treeHeight, currentLevel);
            outOperationsCount++;

            List<Vertex> sortedNeighbors = current.getNeighbors().stream()
                    .filter(v -> !v.isVisited())
                    .sorted(Comparator.comparing(Vertex::getData))
                    .toList();

            boolean isInternal = false;

            for (Vertex neighbor : sortedNeighbors) {
                neighbor.setVisited(true);
                neighbor.setLevel(currentLevel + 1);
                traverse.add(neighbor.getData());
                neighbor.setVisitOrder(visitOrder++);
                collection.add(neighbor);
                inOperationsCount++;
                if (maxQueueSize < collection.size()) maxQueueSize = collection.size();
                isInternal = true;
            }
            if (isInternal) {
                internalCount++;
            } else {
                externalCount++;
            }
        }
        return buildResult(AbstractFirstSearchResult.builder()
                .traverse(traverse)
                .treeHeight(treeHeight)
                .externalCount(externalCount)
                .internalCount(internalCount)
                .inOperationsCount(inOperationsCount)
                .outOperationsCount(outOperationsCount)
                .maxQueueSize(maxQueueSize)
                .build());
    }

    protected abstract R buildResult(AbstractFirstSearchResult result);
}
