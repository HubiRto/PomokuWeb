package pl.pomoku.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pomoku.backend.entity.BFSEntity;

import java.util.Optional;

public interface BFSEntityRepository extends JpaRepository<BFSEntity, Long> {
    Optional<BFSEntity> findByHash(String hash);
}
