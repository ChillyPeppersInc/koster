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
import ru.ChillyPeppersInc.koster.Services.CommentService;
import ru.ChillyPeppersInc.koster.Services.UserService;
import ru.ChillyPeppersInc.koster.models.Post;
import ru.ChillyPeppersInc.koster.models.User;
import ru.ChillyPeppersInc.koster.models.Comment;


import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;

    @Autowired
    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @PostMapping("/comment_create/")
    public ResponseEntity<?> createComment(
            Principal principal,
            @RequestParam("user_id") int user_id,
            @RequestParam("content") String content,
            @RequestParam(value = "image", required = false) MultipartFile image,
            HttpServletResponse response) {

        String username = principal.getName();
        User currentUser = userService.findById(user_id).
                orElseThrow(() -> new UsernameNotFoundException(username));

        Comment newComment = commentService.createComment(currentUser, content);

        try {
            commentService.attachImageToComment(newComment, image);
            response.sendRedirect("/user/" + user_id);
            return null;
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ошибка при загрузке изображения");
        }
    }
}
