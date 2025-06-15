package ru.ChillyPeppersInc.koster.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ChillyPeppersInc.koster.Services.SubscribeService;

import java.security.Principal;

@Controller
@RequestMapping("/api")
public class SubcribeController {

    private final SubscribeService subscribeService;

    public SubcribeController(SubscribeService subscribeService) {
        this.subscribeService = subscribeService;
    }

    @PostMapping("/subcribe")
    public ResponseEntity<?> subcribe(
            @RequestParam("profileUserId") Integer profileUserId,
            Principal principal) {
        return subscribeService.toggleSubscribe(profileUserId, principal);
    }
}
