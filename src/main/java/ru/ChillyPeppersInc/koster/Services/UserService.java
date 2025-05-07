package ru.ChillyPeppersInc.koster.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ChillyPeppersInc.koster.dto.RegistrationDto;
import ru.ChillyPeppersInc.koster.repositories.UserRepository;
import ru.ChillyPeppersInc.koster.models.User;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerNewUser(RegistrationDto registrationDto) {
        System.out.println(userRepository.findByEmail(registrationDto.getEmail()));
//        if (userRepository.findByEmail(registrationDto.getEmail()).isPresent()) {
//            throw new RuntimeException("Username already exists");
//        }

        if (userRepository.findByEmail(registrationDto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setName(registrationDto.getName());
        user.setSurname(registrationDto.getSurname());
        user.setPassword(registrationDto.getPassword());
        user.setEmail(registrationDto.getEmail());

//        Role userRole = roleRepository.findByName("ROLE_USER")
//                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//        user.setRoles(Collections.singleton(userRole));

        return userRepository.save(user);
    }

}
