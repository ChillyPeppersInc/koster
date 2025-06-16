package ru.ChillyPeppersInc.koster.Services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ChillyPeppersInc.koster.models.Subscriber;
import ru.ChillyPeppersInc.koster.models.User;
import ru.ChillyPeppersInc.koster.repositories.SubscribeRepository;

import java.security.Principal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
public class SubscribeService {

    private final UserService userService;
    private final SubscribeRepository subscribeRepository;

    public SubscribeService(UserService userService, SubscribeRepository subscribeRepository) {
        this.userService = userService;
        this.subscribeRepository = subscribeRepository;
    }

    @Transactional
    public ResponseEntity<?> toggleSubscribe(Integer profileUserId, Principal principal) {
        User currentUser = userService.findByUsername(principal.getName()).
                orElseThrow(() -> new UsernameNotFoundException(principal.getName()));
        User profileUser = userService.findById(profileUserId).
                orElseThrow(() -> new UsernameNotFoundException(principal.getName()));
        Optional<Subscriber> existingSubscriber = subscribeRepository.findBySubscriberAndUser(currentUser, profileUser);
        if(existingSubscriber.isPresent()) {
            subscribeRepository.delete(existingSubscriber.get());
            currentUser.setFollowsCount(currentUser.getFollowsCount() - 1);
            profileUser.setFollowersCount(profileUser.getFollowersCount() - 1);
            return ResponseEntity.ok(Map.of("message", "Subscribe removed",
                    "currentUserFollows", subscribeRepository.countBySubscriber(currentUser),
                    "profileUserFollowers", subscribeRepository.countByUser(profileUser)));
        }else{
            Subscriber subscriber = new Subscriber();
            subscriber.setSubscriber(currentUser);
            subscriber.setUser(profileUser);
            subscriber.setCreatedAt(Date.valueOf(LocalDateTime.now().toLocalDate()));
            subscribeRepository.save(subscriber);
            currentUser.setFollowsCount(currentUser.getFollowsCount() + 1);
            profileUser.setFollowersCount(profileUser.getFollowersCount() + 1);
            return ResponseEntity.ok(Map.of("message", "Subscribe added",
                    "currentUserFollows", subscribeRepository.countBySubscriber(currentUser),
                    "profileUserFollowers", subscribeRepository.countByUser(profileUser)));
        }
    }
}
