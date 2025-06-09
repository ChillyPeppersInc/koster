package ru.ChillyPeppersInc.koster.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.ChillyPeppersInc.koster.models.Post;
import ru.ChillyPeppersInc.koster.models.User;
import ru.ChillyPeppersInc.koster.Services.PostService;
import ru.ChillyPeppersInc.koster.Services.UserService;

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
            @AuthenticationPrincipal User currentUser,
            Model model) {

        // Находим пользователя по username
        User profileUser = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Получаем посты пользователя (можно добавить пагинацию)
        List<Post> userPosts = postService.findByUser(profileUser);

        // Добавляем данные в модель
        model.addAttribute("profileUser", profileUser);
        model.addAttribute("posts", userPosts);
        model.addAttribute("isCurrentUser", profileUser.getId() == currentUser.getId());

        return "user/profile"; // Шаблон profile.html в templates/user/
    }
}