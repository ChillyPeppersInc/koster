package ru.ChillyPeppersInc.koster.Services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.ChillyPeppersInc.koster.models.Post;
import ru.ChillyPeppersInc.koster.models.User;
import ru.ChillyPeppersInc.koster.repositories.PostRepository;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final FileStorageService fileStorageService;

    public PostService(PostRepository postRepository, FileStorageService fileStorageService) {
        this.postRepository = postRepository;
        this.fileStorageService = fileStorageService;}

    @Transactional
    public Post createPost(User user, String content, String geolocation) {
        Post post = new Post();
        post.setUser(user);
        post.setContent(content);
        post.setGeolocation(geolocation);
        post.setCreatedAt(Date.valueOf(LocalDate.now()));
        post.setUpdatedAt(Date.valueOf(LocalDate.now()));
        post.setPublic(true);
        post.setStatus("active");
        return postRepository.save(post);
    }

    @Transactional
    public void attachImageToPost(Post post, MultipartFile image) throws IOException {
        if (image != null && !image.isEmpty()) {
            String imageUrl = fileStorageService.storeFile(image, post.getId());
            post.setImage(imageUrl);
            updatePost(post);
        }
    }

    public List<Post> findAllByUser(User user) {
        return postRepository.findByUser(user);
    }

    public Post findById(Integer id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
    }

    @Transactional
    public void deletePost(Integer id) {
        postRepository.deleteById(id);
    }

    @Transactional
    public Post updatePost(Post post) {
        Post existingPost = findById(post.getId());
        existingPost.setContent(post.getContent());
        existingPost.setImage(post.getImage());
        existingPost.setGeolocation(post.getGeolocation());
        existingPost.setUpdatedAt(post.getUpdatedAt());
        existingPost.setPublic(post.isPublic());
        existingPost.setStatus(post.getStatus());
        existingPost.setUser(post.getUser());

        postRepository.delete(post);

        return postRepository.save(existingPost);
    }

    public List<Post> findByUser(User user) {
        return postRepository.findByUser(user);
    }

    public List<Post> getAll(){
        return postRepository.findAll();
    }
}
