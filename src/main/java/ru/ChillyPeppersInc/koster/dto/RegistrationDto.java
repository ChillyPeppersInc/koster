package ru.ChillyPeppersInc.koster.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record RegistrationDto(
        @NotBlank(message = "Name is required")
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
        String name,

        @NotBlank(message = "Surname is required")
        @Size(min = 2, max = 50, message = "Surname must be between 2 and 50 characters")
        String surname,

        @NotBlank(message = "Username is required")
        @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
        @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username can only contain letters, numbers and underscores")
        String username,

        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
        @Pattern(
                regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
                message = "Password must contain at least one digit, one lowercase, one uppercase letter and one special character"
        )
        String password,

        @NotBlank(message = "Birthdate is required")
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Birthdate must be in YYYY-MM-DD format")
        String birthdate
) {
    // Дополнительный метод для удобства преобразования
    public LocalDate getBirthdateAsLocalDate() {
        return LocalDate.parse(birthdate);
    }
}