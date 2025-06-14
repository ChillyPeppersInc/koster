package ru.ChillyPeppersInc.koster.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.ChillyPeppersInc.koster.models.Post;
import ru.ChillyPeppersInc.koster.models.User;
import ru.ChillyPeppersInc.koster.Services.PostService;
import ru.ChillyPeppersInc.koster.Services.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class UserProfileController {

    private final UserService userService;
    private final PostService postService;

    public UserProfileController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/user/{username}")
    public String getUserProfile(
            @PathVariable String username,
            Principal principal,
            Model model) {

        String currentUsername = principal.getName();
        User currentUser = userService.findByUsername(currentUsername).
                orElseThrow(() -> new UsernameNotFoundException(currentUsername));

        // Находим пользователя по username
        User profileUser = userService.findByUsernameOrId(username)
                .orElseThrow(() -> new RuntimeException("User not found"));


        // Добавляем данные в модель
        model.addAttribute("profileUser", profileUser);
        if(profileUser.getId() == currentUser.getId()){
            System.out.println("Ainaz");
            return "redirect:/profile";
        }
        model.addAttribute("user", currentUser);


        return "user"; // Шаблон profile.html в templates/user/
    }
}