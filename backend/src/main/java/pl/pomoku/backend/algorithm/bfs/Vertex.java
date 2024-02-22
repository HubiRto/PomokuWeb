package pl.pomoku.backend.algorithm.bfs;

import lombok.Data;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

@Data
public class Vertex {
    private final int data;
    private boolean visited;
    private int level;
    private int visitOrder;
    @ToString.Exclude
    private List<Vertex> neighbors = new LinkedList<>();
}
