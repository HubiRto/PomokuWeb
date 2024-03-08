package pl.pomoku.backend.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pomoku.backend.entity.algorithmEntity.BreathFirstSearchEntity;

import java.util.Optional;

@Repository
public interface BreathFirstSearchEntityRepository extends JpaRepository<BreathFirstSearchEntity, Long> {
    Optional<BreathFirstSearchEntity> findByHash(String hash);
}
