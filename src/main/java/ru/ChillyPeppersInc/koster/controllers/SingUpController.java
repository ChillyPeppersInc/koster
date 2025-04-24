package ru.ChillyPeppersInc.koster.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SingUpController {
    @GetMapping("/register")
    public String singUp() {
        return "register.html";
    }
}
