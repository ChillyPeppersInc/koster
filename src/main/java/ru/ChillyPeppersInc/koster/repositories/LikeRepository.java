package ru.ChillyPeppersInc.koster.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ChillyPeppersInc.koster.models.Comment;
import ru.ChillyPeppersInc.koster.models.Like;
import ru.ChillyPeppersInc.koster.models.Post;
import ru.ChillyPeppersInc.koster.models.User;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Integer> {
    Optional<Like> findByUserAndPost(User user, Post post);
    Optional<Like> findByUserAndComment(User user, Comment comment);
    int countByPost(Post post);
    int countByComment(Comment comment);
//    boolean existsByUser(User user);
//    boolean existByUserAndPost(User user, Post post);
//    boolean existByUserAndComment(User user, Comment comment);
}
