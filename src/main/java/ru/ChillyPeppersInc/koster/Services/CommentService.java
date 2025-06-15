package ru.ChillyPeppersInc.koster.Services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.ChillyPeppersInc.koster.models.Comment;
import ru.ChillyPeppersInc.koster.models.Post;
import ru.ChillyPeppersInc.koster.models.User;
import ru.ChillyPeppersInc.koster.repositories.CommentRepository;
import ru.ChillyPeppersInc.koster.repositories.PostRepository;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final FileStorageService fileStorageService;

    public CommentService(CommentRepository commentRepository, FileStorageService fileStorageService) {
        this.commentRepository = commentRepository;
        this.fileStorageService = fileStorageService;}

    @Transactional
    public Comment createComment(User ownerUser, User writerUser, String content, boolean isAnonimous) {
        Comment comment = new Comment();
        comment.setWriterUser(writerUser);
        comment.setContent(content);
        comment.setCreatedAt(LocalDate.now());
        comment.setUpdatedAt(LocalDate.now());
        comment.setStatus("active");
        comment.setAnonimous(isAnonimous);
        comment.setOwnerUser(ownerUser);
        return commentRepository.save(comment);
    }

    @Transactional
    public void attachImageToComment(Comment comment, MultipartFile image) throws IOException {
        if (image != null && !image.isEmpty()) {
            String imageUrl = fileStorageService.storeFile(image, comment.getId());
            comment.setImage(imageUrl);
            updateComment(comment);
        }
    }

    public List<Comment> findAllByOwnerUser(User user) {
        return commentRepository.findByOwnerUser(user);
    }

    public Comment findById(Integer id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));
    }

    @Transactional
    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }

    @Transactional
    public void updateComment(Comment comment) {
//        Post existingPost = findById(post.getId());
//        existingPost.setContent(post.getContent());
//        existingPost.setImage(post.getImage());
//        existingPost.setGeolocation(post.getGeolocation());
//        existingPost.setUpdatedAt(post.getUpdatedAt());
//        existingPost.setPublic(post.isPublic());
//        existingPost.setStatus(post.getStatus());
//        existingPost.setUser(post.getUser());
        commentRepository.save(comment);
    }
}
