package ru.ChillyPeppersInc.koster.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ChillyPeppersInc.koster.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameOrEmail(String username, String email);
    List<User> findByNameContainingIgnoreCaseOrUsernameContainingIgnoreCase(String name, String username);
}
