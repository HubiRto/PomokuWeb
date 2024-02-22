package pl.pomoku.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BFSEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hash;
    private int treeHeight;
    private int maxQueueSize;
    private int internalCount;
    private int externalCount;
    private int inOperationsCount;
    private int outOperationsCount;
    @ElementCollection
    private List<Integer> traverse = new ArrayList<>();
}
