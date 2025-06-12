package ru.ChillyPeppersInc.koster.Services;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.ChillyPeppersInc.koster.dto.SettingsDto;
import ru.ChillyPeppersInc.koster.models.Post;
import ru.ChillyPeppersInc.koster.models.User;
import ru.ChillyPeppersInc.koster.repositories.UsersRepository;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class SettingsService {
    private final UsersRepository userRepository;
    private static final String UPLOAD_DIR = "src/main/resources/static/images/avatars/";

    public SettingsService(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    public int updateUser(User user, SettingsDto settingsDto) {
        user.setName(settingsDto.name());
        user.setSurname(settingsDto.surname());
        user.setBirthdate(settingsDto.getBirthdateAsLocalDate());
        user.setAcademicGroup(settingsDto.academicGroup());
        user.setBio(settingsDto.bio());
        user.setUpdatedAt(LocalDate.now());
        user.setAvatar("images/avatars/" + getImage(settingsDto));

        userRepository.save(user);

        return 1;
    }

    private String getImage(SettingsDto settingsDto) {
        MultipartFile file = settingsDto.avatar();
        validateImageFile(file);

        String filename = generateFilename(file.getOriginalFilename());
        try {
            saveToFileSystem(file.getBytes(), filename);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return filename;
    }

    private void validateImageFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }
        if (!file.getContentType().startsWith("image/")) {
            throw new RuntimeException("Only image files are allowed");
        }
    }

    private Path saveToFileSystem(byte[] bytes, String filename) throws IOException {
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        Path filePath = uploadPath.resolve(filename);

        return Files.write(filePath, bytes);
    }

    private String generateFilename(String originalFilename) {
        return UUID.randomUUID().toString() + "_" + originalFilename;
    }
}
