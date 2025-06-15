package ru.ChillyPeppersInc.koster.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ChillyPeppersInc.koster.models.Subscriber;
import ru.ChillyPeppersInc.koster.models.User;

import java.util.Optional;

public interface SubscribeRepository extends JpaRepository<Subscriber, Integer> {
    Optional<Subscriber> findByUser(User user);
    Optional<Subscriber> findBySubscriber(Subscriber subscriber);
    Optional<Subscriber> findBySubscriberAndUser(User currentUser, User profileUser);
    boolean existsBySubscriberAndUser(User currentUser, User profileUser);
    int countBySubscriber(User currentUser);
    int countByUser(User profileUser);
}
