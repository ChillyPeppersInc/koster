package ru.ChillyPeppersInc.koster.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ChillyPeppersInc.koster.Services.FileStorageService;
import ru.ChillyPeppersInc.koster.Services.PostService;
import ru.ChillyPeppersInc.koster.Services.UserService;
import ru.ChillyPeppersInc.koster.models.Post;
import ru.ChillyPeppersInc.koster.models.User;


import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;
    private final FileStorageService fileStorageService;
    private final UserService userService;

    @Autowired
    public PostController(PostService postService, FileStorageService fileStorageService, UserService userService) {
        this.postService = postService;
        this.fileStorageService = fileStorageService;
        this.userService = userService;
    }

    @PostMapping("/post_create")
    public ResponseEntity<?> createPost(
            @AuthenticationPrincipal User currentUser,
            @RequestParam("content") String content,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "geolocation", required = false) String geolocation) {
        try {
            // Создаем пост
            Post post = new Post();
            post.setUser(currentUser);
            post.setContent(content);
            post.setGeolocation(geolocation);
            post.setCreatedAt(Date.valueOf(LocalDate.now()));
            post.setUpdatedAt(Date.valueOf(LocalDate.now()));
            post.setPublic(true); // или можно передавать параметром
            post.setStatus("active");
            // Сохраняем пост, чтобы получить ID
            Post savedPost = postService.createPost(post);

            // Если есть изображение, сохраняем его и обновляем пост
            if (image != null && !image.isEmpty()) {
                String imageUrl = fileStorageService.storeFile(image, savedPost.getId());
                savedPost.setImage(imageUrl);
                postService.updatePost(savedPost);
            }

            currentUser.getPosts().add(post);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ошибка при загрузке изображения");
        }
    }
}
