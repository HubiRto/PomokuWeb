package pl.pomoku.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pomoku.backend.entity.algorithmEntity.SequenceEntity;

public interface SequenceRepository extends JpaRepository<SequenceEntity, Long> {

}
