package pl.pomoku.backend.entity.algorithmEntity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Inheritance(strategy = InheritanceType.JOINED)
@ToString
public class AbstractFirstSearchEntity {
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
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "traverse")
    private List<Integer> traverse = new ArrayList<>();
}
