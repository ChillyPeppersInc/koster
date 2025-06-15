package ru.ChillyPeppersInc.koster.Services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ChillyPeppersInc.koster.models.User;

import java.security.Principal;

@Service
public class SubscribeService {

    private final UserService userService;

    public SubscribeService(UserService userService) {
        this.userService = userService;
    }

//    @Transactional
//    public ResponseEntity<?> toggleSubscribe(Integer profileUserId, Principal principal) {
//        User user = userService.findByUsername(principal.getName()).
//                orElseThrow(() -> new UsernameNotFoundException(principal.getName()));
//    }
}
