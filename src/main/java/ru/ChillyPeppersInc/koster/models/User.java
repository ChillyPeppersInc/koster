package ru.ChillyPeppersInc.koster.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 50, message = "Name length sould be between 2 and 50")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Surname shouldn't be empty")
    @Size(min = 2, max = 50, message = "Surname length sould be between 2 and 50")
    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    @NotEmpty(message = "Email shouldn't be empty")
    @Email
    private String email;

    @Column(name = "date_of_birthday")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    private String dateOfBirthday;

    @Column(name = "password")
    @NotEmpty(message = "Password shouldn't be empty")
    private String password;

    public User(String name, String surname, String mail, String dateOfBirthday, String password) {
        this.name = name;
        this.surname = surname;
        this.email = mail;
        this.dateOfBirthday = dateOfBirthday;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String mail) {
        this.email = mail;
    }

    public String getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(String dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());;
    }
    public String getHashedPassword() {
        return password;
    }
}
