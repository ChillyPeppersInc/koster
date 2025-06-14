package ru.ChillyPeppersInc.koster.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record RegistrationDto(
        @NotBlank(message = "Имя должно быть заполнено")
        @Size(min = 2, max = 50, message = "Длина имени должна быть между 2 и 50 символами")
        String name,

        @NotBlank(message = "Фамилия должна быть заполнена")
        @Size(min = 2, max = 50, message = "Длина фамилии должна быть между 2 и 50 символами")
        String surname,

        @NotBlank(message = "Логин должен быть заполнен")
        @Size(min = 3, max = 20, message = "Длина логина должна быть между 3 и 50 символами")
        @Pattern(regexp = "^(?!\\\\d+$)[a-zA-Z0-9_]+$", message = "Логин может содержать только буквы, цифры и нижние подчеркивания")
        String username,

        @NotBlank(message = "Почта должна быть заполнена")
        @Email(message = "Некорректная почта")
        String email,

        @NotBlank(message = "Пароль должен быть заполнен")
        @Size(min = 8, max = 100, message = "Длина пароля должна быть между 8 и 100 символами")
        @Pattern(
                regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
                message = "Пароль должен содержать как минимум одну цифру, одну строчную, одну заглавную букву и один специальный символ"
        )
        String password,

        @NotBlank(message = "День рождения должен быть заполнен")
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Дата рождения должна быть указана в формате ГГГГ-ММ-ДД")
        String birthdate,

        @NotBlank(message = "Группа должна быть заполнена")
        String academicGroup
) {
    // Дополнительный метод для удобства преобразования
    public LocalDate getBirthdateAsLocalDate() {
        return LocalDate.parse(birthdate);
    }
}