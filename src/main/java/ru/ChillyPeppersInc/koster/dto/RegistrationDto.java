package ru.ChillyPeppersInc.koster.dto;

public record RegistrationDto(String name, String surname, String email,
                              String dateOfBirthday, String password) { }