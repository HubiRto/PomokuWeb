package pl.pomoku.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pomoku.backend.entity.BreathFirstSearchEntity;
import pl.pomoku.backend.entity.DepthFirstSearchEntity;

import java.util.Optional;

public interface DepthFirstSearchEntityRepository extends JpaRepository<DepthFirstSearchEntity, Long> {
    Optional<DepthFirstSearchEntity> findByHash(String hash);
}
