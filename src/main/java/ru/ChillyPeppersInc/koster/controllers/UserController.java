package ru.ChillyPeppersInc.koster.controllers;


import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ChillyPeppersInc.koster.Services.UserService;
import ru.ChillyPeppersInc.koster.dao.UserDAO;
import ru.ChillyPeppersInc.koster.dto.RegistrationDto;
import ru.ChillyPeppersInc.koster.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/search")
    public String searchUsers(@RequestParam String query, Principal principal, Model model) {
        String username = principal.getName();
        User currentUser = userService.findByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException(username));

        List<User> foundUsers = userService.searchUsers(query);
        model.addAttribute("foundUsers", foundUsers);
        model.addAttribute("searchQuery", query);
        model.addAttribute("user", currentUser);
        return "search-results"; // имя шаблона для отображения результатов
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/feed";
        }
        model.addAttribute("registartionDto", new RegistrationDto("", "", "", "", "", "", ""));
        return "register";
    }

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String registerUser(
            @ModelAttribute("registrationDto") @Valid RegistrationDto registrationDto,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getFieldError().getDefaultMessage());
            return "register"; // Остаемся на странице с отображением ошибок
        }

        try {
            userService.registerNewUser(registrationDto);
            return "redirect:/login?registered";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }
}
