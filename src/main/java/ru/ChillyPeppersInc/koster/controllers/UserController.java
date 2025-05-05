package ru.ChillyPeppersInc.koster.controllers;


import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.ChillyPeppersInc.koster.dao.UserDAO;
import ru.ChillyPeppersInc.koster.models.User;

@Controller
public class UserController {
    @GetMapping("/registration")
    public String singUp(@ModelAttribute("user") User user) {
        return "register.html";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register.html";
        }
        UserDAO.save(user);
        return "index.html";
    }

}
