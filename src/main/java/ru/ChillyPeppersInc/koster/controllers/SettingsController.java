package ru.ChillyPeppersInc.koster.controllers;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import ru.ChillyPeppersInc.koster.Services.SettingsService;
import ru.ChillyPeppersInc.koster.dto.RegistrationDto;
import ru.ChillyPeppersInc.koster.dto.SettingsDto;
import ru.ChillyPeppersInc.koster.models.User;
import ru.ChillyPeppersInc.koster.repositories.UsersRepository;

import java.io.IOException;

@Controller
public class SettingsController {

    private final UsersRepository userRepository;
    private final SettingsService settingsService;

    public SettingsController(UsersRepository userRepository, SettingsService settingsService) {
        this.userRepository = userRepository;
        this.settingsService = settingsService;
    }

    @GetMapping("/settings")
    public String settingsPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        model.addAttribute("user", user);
        model.addAttribute("settingsDto", new SettingsDto(null, "", "", "", "", ""));
        return "settings";
    }

    @PostMapping("/settings")
    public String updateUser(@AuthenticationPrincipal UserDetails userDetails,
                             @Valid @ModelAttribute("settingsDto") SettingsDto settingsDto,
                             BindingResult bindingResult,
                             Model model) {
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        model.addAttribute("user", user);
        if (bindingResult.hasErrors()) {
            // Обработка ошибок валидации
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "settings";
        }

        try {
            settingsService.updateUser(user, settingsDto);
            model.addAttribute("success", "Настройки успешно обновлены");
            return "redirect:/settings?success";
        } catch (Exception e) {
            model.addAttribute("error", "Ошибка при загрузке аватара");
            return "redirect:/settings?failed";
        }
    }

}
