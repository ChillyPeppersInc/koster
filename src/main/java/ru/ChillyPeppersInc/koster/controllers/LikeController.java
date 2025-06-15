package ru.ChillyPeppersInc.koster.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ChillyPeppersInc.koster.Services.LikeService;
import ru.ChillyPeppersInc.koster.repositories.LikeRepository;

import java.security.Principal;

@RestController
@RequestMapping("/api/like")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/post")
    public ResponseEntity<?> togglePostLike(
            @RequestParam("postId") Integer postId,
            Principal principal){
        return likeService.toggleLikeOnPost(postId, principal);
    }

    @PostMapping("/comment")
    public ResponseEntity<?> toggleCommentLike(
            @RequestParam("commentId") Integer commentId,
            Principal principal){
        return likeService.toggleLikeOnComment(commentId, principal);
    }


}
