package pl.pomoku.backend.entity.algorithmEntity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@AllArgsConstructor
public class BreathFirstSearchEntity extends AbstractFirstSearchEntity {
    public BreathFirstSearchEntity(int treeHeight, int maxQueueSize, int internalCount, int externalCount, int inOperationsCount, int outOperationsCount, List<Integer> traverse) {
        super(null, null, treeHeight, maxQueueSize, internalCount, externalCount, inOperationsCount, outOperationsCount, traverse);
    }
}
