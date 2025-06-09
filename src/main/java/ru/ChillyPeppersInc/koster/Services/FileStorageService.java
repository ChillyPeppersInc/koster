package ru.ChillyPeppersInc.koster.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${post.upload-dir}")
    private String uploadDir;

    @Value("${post.static-url-prefix}")
    private String urlPrefix;

    public String storeFile(MultipartFile file, Integer postId) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }

        // Создаем папку, если ее нет
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Генерируем уникальное имя файла (чтобы избежать перезаписи)
        String originalFileName = file.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String uniqueFileName = postId + "_" + UUID.randomUUID() + fileExtension;

        // Сохраняем файл
        Path filePath = uploadPath.resolve(uniqueFileName);
        Files.copy(file.getInputStream(), filePath);

        // Возвращаем URL для доступа к файлу
        return urlPrefix + "/" + uniqueFileName;
    }

    // Удаление файла (если пост удаляется)
    public void deleteFile(String fileUrl) throws IOException {
        if (fileUrl == null || !fileUrl.startsWith(urlPrefix)) {
            return;
        }

        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        Path filePath = Paths.get(uploadDir, fileName);

        if (Files.exists(filePath)) {
            Files.delete(filePath);
        }
    }
}