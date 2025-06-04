package ru.ChillyPeppersInc.koster.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 50, message = "Name length should be between 2 and 50")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Surname shouldn't be empty")
    @Size(min = 2, max = 50, message = "Surname length should be between 2 and 50")
    @Column(name = "surname")
    private String surname;

    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 1, max = 50, message = "Name length should be between 1 and 50")
    @Column(name = "user_name", unique = true)
    private String userName;

    @Column(name = "email", unique = true)
    @NotEmpty(message = "Email shouldn't be empty")
    @Email
    private String email;

    @Column(name = "date_of_birthday")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    // работает с типами java.util.Date, java.util.Calendar, java.time.*
    @Past
    private LocalDate birthdate;

    @Column(name = "password")
    @NotEmpty(message = "Password shouldn't be empty")
    private String password;

    public User(String name, String surname, String userName, String mail, LocalDate dateOfBirthday, String password) {
        this.name = name;
        this.surname = surname;
        this.email = mail;
        this.birthdate = dateOfBirthday;
        this.userName = userName;
        setPassword(password);
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String mail) {
        this.email = mail;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
