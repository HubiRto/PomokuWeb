package pl.pomoku.backend.algorithm.bfs;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.pomoku.backend.entity.BFSEntity;

import java.util.*;

@AllArgsConstructor
@Data
public class BreathFirstSearch {
    private final Vertex startVertex;
    private static int maxQueueSize = 0;
    private static int treeHeight = 0;
    private static int internalCount = 0;
    private static int externalCount = 0;
    private static int outOperationsCount = 0;
    private static int inOperationsCount = 0;
    private static int visitOrder = 0;

    public BreathFirstSearch(int[][] graph, int startIndex) {
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

    public BFSEntity traverse() {
        List<Integer> traverse = new ArrayList<>();
        Queue<Vertex> queue = new LinkedList<>();
        startVertex.setVisited(true);
        startVertex.setLevel(0);
        traverse.add(startVertex.getData());
        queue.add(startVertex);
        inOperationsCount++;
        if (maxQueueSize < queue.size()) maxQueueSize = queue.size();

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            System.out.println(current);
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
                queue.add(neighbor);
                inOperationsCount++;
                if (maxQueueSize < queue.size()) maxQueueSize = queue.size();
                isInternal = true;
            }

            if (isInternal) {
                internalCount++;
            } else {
                externalCount++;
            }
        }

        return BFSEntity.builder()
                .traverse(traverse)
                .treeHeight(treeHeight)
                .externalCount(externalCount)
                .internalCount(internalCount)
                .inOperationsCount(inOperationsCount)
                .outOperationsCount(outOperationsCount)
                .maxQueueSize(maxQueueSize)
                .build();
    }
}
