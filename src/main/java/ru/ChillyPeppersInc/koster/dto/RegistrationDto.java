package ru.ChillyPeppersInc.koster.dto;
import java.util.Date;
public record RegistrationDto(String name, String surname, String username,
                              String email, String password, String birthdate) {

}