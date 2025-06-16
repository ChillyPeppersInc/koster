package ru.ChillyPeppersInc.koster.Services;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ChillyPeppersInc.koster.models.Comment;
import ru.ChillyPeppersInc.koster.models.Like;
import ru.ChillyPeppersInc.koster.models.Post;
import ru.ChillyPeppersInc.koster.models.User;
import ru.ChillyPeppersInc.koster.repositories.LikeRepository;
import ru.ChillyPeppersInc.koster.repositories.PostRepository;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final PostService postService;
    private final CommentService commentService;
    private final UserService userService;

    public LikeService(LikeRepository likeRepository, PostService postService, CommentService commentService, UserService userService) {
        this.likeRepository = likeRepository;
        this.postService = postService;
        this.commentService = commentService;
        this.userService = userService;
    }


    @Transactional
    public ResponseEntity<?> toggleLikeOnPost(Integer postId, Principal principal) {
        User user = userService.findByUsername(principal.getName()).
                orElseThrow(() -> new UsernameNotFoundException(principal.getName()));
        Post post = postService.findById(postId);

        Optional<Like> existingLike = likeRepository.findByUserAndPost(user, post);

        if(existingLike.isPresent()) {
            likeRepository.delete(existingLike.get());
            post.setLikes(post.getLikes() - 1);
            return ResponseEntity.ok(Map.of("message", "Like removed", "likesCount", likeRepository.countByPost(post)));
        } else{
            Like like = new Like();
            like.setUser(user);
            like.setPost(post);
            post.setLikes(post.getLikes() + 1);
            likeRepository.save(like);
            return ResponseEntity.ok(Map.of("message", "Like added", "likesCount", likeRepository.countByPost(post)));
        }
    }

    @Transactional
    public ResponseEntity<?> toggleLikeOnComment(Integer commentId, Principal principal) {
        User user = userService.findByUsername(principal.getName()).
                orElseThrow(() -> new UsernameNotFoundException(principal.getName()));
        Comment comment = commentService.findById(commentId);

        Optional<Like> existingLike = likeRepository.findByUserAndComment(user, comment);

        if(existingLike.isPresent()) {
            likeRepository.delete(existingLike.get());
            comment.setLikes(comment.getLikes() - 1);
            return ResponseEntity.ok(Map.of("message", "Like removed", "likesCount", likeRepository.countByComment(comment)));
        } else{
            Like like = new Like();
            like.setUser(user);
            like.setComment(comment);
            comment.setLikes(comment.getLikes() + 1);
            likeRepository.save(like);
            return ResponseEntity.ok(Map.of("message", "Like added", "likesCount", likeRepository.countByComment(comment)));
        }
    }

    public ResponseEntity<?> postLikeExist(Integer postId, Principal principal) {
        User user = userService.findByUsername(principal.getName()).
                orElseThrow(() -> new UsernameNotFoundException(principal.getName()));
        Post post = postService.findById(postId);
        return ResponseEntity.ok(likeRepository.existByUserAndPost(user, post));
    }

    public ResponseEntity<?> commentLikeExist(Integer commentId, Principal principal) {
        User user = userService.findByUsername(principal.getName()).
                orElseThrow(() -> new UsernameNotFoundException(principal.getName()));
        Comment comment = commentService.findById(commentId);
        return ResponseEntity.ok(likeRepository.existByUserAndComment(user, comment));
    }

    public int getPostLikesCount(Integer postId) {
        Post post = postService.findById(postId);
        return likeRepository.countByPost(post);
    }

    public int getCommentLikesCount(Integer commentId) {
        Comment comment = commentService.findById(commentId);
        return likeRepository.countByComment(comment);
    }
}
