package ru.ChillyPeppersInc.koster.controllers;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ChillyPeppersInc.koster.models.User;

@Controller
public class StartController {

    @GetMapping("/")
    public String welcomePage(Model model,
                              Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/feed";
        }
        return "index";
    }
}
