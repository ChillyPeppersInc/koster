package ru.ChillyPeppersInc.koster.Services;


import org.springframework.stereotype.Service;
import ru.ChillyPeppersInc.koster.models.User;
import ru.ChillyPeppersInc.koster.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;

@Service
public class LoginService {

    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean checkpassword(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()){
            userOptional = userRepository.findByEmail(email);
            if (userOptional.isEmpty()){
                return false;
            }
        }

        User user = userOptional.get();
        return BCrypt.checkpw(password, user.getPassword());
    }
}
