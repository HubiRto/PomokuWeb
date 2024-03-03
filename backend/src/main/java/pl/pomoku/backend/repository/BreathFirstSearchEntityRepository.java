package pl.pomoku.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pomoku.backend.entity.algorithmEntity.BreathFirstSearchEntity;

import java.util.Optional;

public interface BreathFirstSearchEntityRepository extends JpaRepository<BreathFirstSearchEntity, Long> {
    Optional<BreathFirstSearchEntity> findByHash(String hash);
}
