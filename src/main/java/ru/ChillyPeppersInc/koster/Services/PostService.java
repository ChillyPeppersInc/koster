package ru.ChillyPeppersInc.koster.Services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ChillyPeppersInc.koster.models.Post;
import ru.ChillyPeppersInc.koster.models.User;
import ru.ChillyPeppersInc.koster.repositories.PostRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public Post createPost(Post post) {
        return postRepository.save(post);
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
        return postRepository.save(existingPost);
    }
}
