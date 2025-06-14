package ru.ChillyPeppersInc.koster.controllers;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ChillyPeppersInc.koster.Services.FileStorageService;
import ru.ChillyPeppersInc.koster.Services.PostService;
import ru.ChillyPeppersInc.koster.Services.UserService;
import ru.ChillyPeppersInc.koster.models.Post;
import ru.ChillyPeppersInc.koster.models.User;


import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    private final UserService userService;

    @Autowired
    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping("/post_create")
    public ResponseEntity<?> createPost(
            Principal principal,
            @RequestParam("content") String content,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "geolocation", required = false) String geolocation,
            HttpServletResponse response) {

        String username = principal.getName();
        User currentUser = userService.findByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException(username));

        Post newPost = postService.createPost(currentUser, content, geolocation);

        try {
            postService.attachImageToPost(newPost, image);
            response.sendRedirect("/profile");
            return null;
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ошибка при загрузке изображения");
        }
    }
}
