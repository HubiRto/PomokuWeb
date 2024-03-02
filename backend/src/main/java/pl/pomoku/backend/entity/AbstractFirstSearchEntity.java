package pl.pomoku.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Inheritance(strategy = InheritanceType.JOINED)
public class AbstractFirstSearchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_sequence")
//    @SequenceGenerator(name = "entity_sequence", sequenceName = "entity_sequence", allocationSize = 1)
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
