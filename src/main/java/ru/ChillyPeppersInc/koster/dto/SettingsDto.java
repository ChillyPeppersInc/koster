package ru.ChillyPeppersInc.koster.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public record SettingsDto(

        MultipartFile avatar,

        @NotBlank(message = "Имя должно быть заполнено")
        @Size(min = 2, max = 50, message = "Длина имени должна быть между 2 и 50 символами")
        String name,

        @NotBlank(message = "Фамилия должна быть заполнена")
        @Size(min = 2, max = 50, message = "Длина фамилии должна быть между 2 и 50 символами")
        String surname,

        @NotBlank(message = "День рождения должен быть заполнен")
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Дата рождения должна быть указана в формате ГГГГ-ММ-ДД")
        String birthdate,

        @NotBlank(message = "Группа должна быть заполнена")
        String academicGroup,

        String bio
) {
    public LocalDate getBirthdateAsLocalDate() {
        return LocalDate.parse(birthdate);
    }
}
