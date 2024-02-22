package pl.pomoku.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.pomoku.backend.entity.HoarePartitionResult;

import java.util.Optional;

public interface HoarePartitionResultRepository extends JpaRepository<HoarePartitionResult, Long> {
    @Query(value = "SELECT * FROM hoare_partition_result WHERE hash_token = :hash", nativeQuery = true)
    Optional<HoarePartitionResult> findByHashToken(String hash);
}
