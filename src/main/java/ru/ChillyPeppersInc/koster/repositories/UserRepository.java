package ru.ChillyPeppersInc.koster.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ChillyPeppersInc.koster.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameOrEmail(String username, String email);
}
