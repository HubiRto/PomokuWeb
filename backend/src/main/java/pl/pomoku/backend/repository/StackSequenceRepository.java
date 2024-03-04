package pl.pomoku.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pomoku.backend.algorithm.stackSequence.StackSequence;
import pl.pomoku.backend.entity.algorithmEntity.StackSequenceEntity;

import java.util.Optional;

public interface StackSequenceRepository extends JpaRepository<StackSequenceEntity, Long> {
    Optional<StackSequenceEntity> findByHash(String hash);
}
