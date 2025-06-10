package ru.ChillyPeppersInc.koster.Services;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.ChillyPeppersInc.koster.dto.SettingsDto;
import ru.ChillyPeppersInc.koster.models.Post;
import ru.ChillyPeppersInc.koster.models.User;
import ru.ChillyPeppersInc.koster.repositories.UsersRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class SettingsService {
    private final UsersRepository userRepository;
    private final Path avatarsLocation = Paths.get("static/images/avatars");

    public SettingsService(UsersRepository userRepository) {
        this.userRepository = userRepository;
        try {
            Files.createDirectories(avatarsLocation);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось создать директорию для аватаров", e);
        }
    }

    public int updateUser(User user, SettingsDto settingsDto) {
        user.setName(settingsDto.name());
        user.setSurname(settingsDto.surname());
        user.setBirthdate(settingsDto.getBirthdateAsLocalDate());
        user.setAcademicGroup(settingsDto.academicGroup());
        user.setBio(settingsDto.bio());
        user.setUpdatedAt(LocalDate.now());
        if (settingsDto.avatar() != null && !settingsDto.avatar().isEmpty()) {
            try {
                String avatarFilename = storeAvatar(settingsDto.avatar());
                user.setAvatar(avatarFilename);
            } catch (IOException e) {
                throw new RuntimeException("Не удалось загрузить автар", e);
            }
        }
        userRepository.save(user);

        return 1;
    }

    private String storeAvatar(MultipartFile avatar) throws IOException {
        // Генерируем уникальное имя файла
        String originalFilename = avatar.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFilename = UUID.randomUUID().toString() + extension;

        // Проверяем, что файл - изображение
        if (!avatar.getContentType().startsWith("image/")) {
            throw new IllegalArgumentException("Загружаемый файл не является изображением");
        }

        // Сохраняем файл
        Path destinationFile = avatarsLocation.resolve(newFilename);
        avatar.transferTo(destinationFile.toFile());

        return newFilename;
    }
}
