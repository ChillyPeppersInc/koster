package ru.ChillyPeppersInc.koster.Services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ChillyPeppersInc.koster.dto.RegistrationDto;
import ru.ChillyPeppersInc.koster.models.Comment;
import ru.ChillyPeppersInc.koster.models.Post;
import ru.ChillyPeppersInc.koster.repositories.UsersRepository;
import ru.ChillyPeppersInc.koster.models.User;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
        private final UsersRepository userRepository;

    public UserService(UsersRepository userRepository) {
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
            String name = registrationDto.name();
            String surname = registrationDto.surname();
            String username = registrationDto.username();
            String email = registrationDto.email();
            String status = "created";
            String avatar = null;
            LocalDate birthdate = registrationDto.getBirthdateAsLocalDate();
            String academicGroup = registrationDto.academicGroup();
            String bio = null;
            int followersCount = 0;
            int followsCount = 0;
            LocalDate createdAt = LocalDate.now();
            LocalDate updatedAt = LocalDate.now();
            LocalDate lastLogin = null;
            String password = setPassword(registrationDto.password());
            ArrayList<Post> posts = new ArrayList<>();
            ArrayList<Comment> comments = new ArrayList<>();
            ArrayList<Comment> writedComments = new ArrayList<>();
            boolean isActive = false;
            LocalDate deletedAt = null;
            User user = new User(posts, username, name, surname, email, avatar, birthdate, academicGroup, bio, followersCount, followsCount, isActive, lastLogin,
                    password, createdAt, updatedAt, deletedAt, status, comments, writedComments);
            userRepository.save(user);
            return ResponseEntity.ok("User registered successfully!");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private String setPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUsernameOrId(String username) {
        int userId;
        try{
            userId = Integer.parseInt(username);
        } catch (NumberFormatException e) {
            return userRepository.findByUsername(username);
        }
        return userRepository.findById(userId);
    }

    public List<User> searchUsers(String query) {
        return userRepository.findByNameContainingIgnoreCaseOrUsernameContainingIgnoreCase(query, query);
    }
}
