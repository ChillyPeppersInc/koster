package ru.ChillyPeppersInc.koster.repositories;

import ru.ChillyPeppersInc.koster.models.Subscriber;
import ru.ChillyPeppersInc.koster.models.User;

import java.util.Optional;

public interface SubscribeRepository {
    Optional<Subscriber> findByUser(User user);
    Optional<Subscriber> findBySubscriber(Subscriber subscriber);
    Optional<Subscriber> findByUserAndSubscriber(User user, Subscriber subscriber);
    int countBySubscriber(Subscriber subscriber);
    int countByUser(User user);
}
