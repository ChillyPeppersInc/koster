package ru.ChillyPeppersInc.koster.Services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ChillyPeppersInc.koster.dto.RegistrationDto;
import ru.ChillyPeppersInc.koster.repositories.UserRepository;
import ru.ChillyPeppersInc.koster.models.User;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class UserService {
        private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> registerNewUser(RegistrationDto registrationDto) {

        if (userRepository.findByEmail(registrationDto.email()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        if (userRepository.findByUsername(registrationDto.username()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        try {
            User user = new User();
            user.setName(registrationDto.name());
            user.setSurname(registrationDto.surname());
            user.setUserName(registrationDto.username());
            user.setBirthdate(registrationDto.getBirthdateAsLocalDate());
            setPassword(user, registrationDto.password());
            user.setEmail(registrationDto.email());
            userRepository.save(user);
            return ResponseEntity.ok("User registered successfully!");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private void setPassword(User user, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(password));
    }
}
