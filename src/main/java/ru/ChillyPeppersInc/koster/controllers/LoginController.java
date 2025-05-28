package ru.ChillyPeppersInc.koster.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.ChillyPeppersInc.koster.Services.LoginService;
import ru.ChillyPeppersInc.koster.dto.LoginDTO;

@Controller
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login.html";
    }

    @PostMapping("/login")
    public String login(LoginDTO loginDTO, Model model) {
        try {
            boolean rightPassword = loginService.checkpassword(// заглушка - проверка происходит по почте, но получается как username
                    loginDTO.getUsername(),
                    loginDTO.getPassword()
            );
            System.out.println(loginDTO.getUsername() + " " + loginDTO.getPassword());

            if (rightPassword) {
                return "redirect:/profile";
            } else {
                model.addAttribute("error", "Invalid username or password.");
                return "login";
            }
        }catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }

    }
}
