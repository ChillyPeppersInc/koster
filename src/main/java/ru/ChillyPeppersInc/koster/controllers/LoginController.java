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
    public String singUp() {
        return "login.html";
    }

    @PostMapping("/login")
    public String login(LoginDTO loginDTO, Model model) {
        boolean rightPassword = loginService.checkpassword(
                loginDTO.getMail(),
                loginDTO.getPassword()
        );

        if (rightPassword) {
            return "redirect:/";
        } else {
            model.addAttribute("error", "Invalid username or password.");
            return "login.html";
        }

    }
}
