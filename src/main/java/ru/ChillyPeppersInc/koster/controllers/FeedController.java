package ru.ChillyPeppersInc.koster.controllers;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ChillyPeppersInc.koster.Services.PostService;
import ru.ChillyPeppersInc.koster.models.Comment;
import ru.ChillyPeppersInc.koster.models.Post;
import ru.ChillyPeppersInc.koster.models.User;
import ru.ChillyPeppersInc.koster.repositories.PostRepository;
import ru.ChillyPeppersInc.koster.repositories.UsersRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FeedController {

    private final PostService postService;
    private final UsersRepository usersRepository;

    public FeedController(PostRepository postRepository, PostService postService, UsersRepository usersRepository) {
        this.postService = postService;
        this.usersRepository = usersRepository;
    }

    @GetMapping("/feed")
    public String feedPage(Model model, Principal principal) {
        List<Post> allPosts = postService.getAll();
        allPosts.stream().distinct().collect(Collectors.toList());
        List<Post> sortedPosts =  allPosts.stream()
                .sorted(Comparator.comparing(Post::getCreatedAt)).collect(Collectors.toList());
        User user = usersRepository.findByUsername(principal.getName()).
                orElseThrow(() -> new UsernameNotFoundException("User not found"));
        model.addAttribute("posts", sortedPosts.reversed());
        model.addAttribute("user", user);
        return "feed";
    }
}
