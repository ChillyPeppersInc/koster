package ru.ChillyPeppersInc.koster.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PasswordResetController {
    @GetMapping("/password_reset")
    public String passwordReset() {
        return "password_reset.html";
    }
}