package pl.pomoku.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pomoku.backend.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
