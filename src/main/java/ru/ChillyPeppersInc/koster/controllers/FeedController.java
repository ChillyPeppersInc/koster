package ru.ChillyPeppersInc.koster.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ChillyPeppersInc.koster.Services.PostService;
import ru.ChillyPeppersInc.koster.models.Post;
import ru.ChillyPeppersInc.koster.repositories.PostRepository;

import java.util.List;

@Controller
public class FeedController {

    private final PostService postService;

    public FeedController(PostRepository postRepository, PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/feed")
    public String feedPage(Model model) {
        List<Post> allPosts = postService.getAll();
        model.addAttribute("posts", allPosts);
        return "feed";
    }
}
